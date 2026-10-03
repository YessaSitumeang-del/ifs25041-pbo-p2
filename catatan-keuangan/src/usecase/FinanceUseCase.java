package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction add(String description, long amount, TransactionType type) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah harus lebih dari 0.");
        }
        Transaction t = new Transaction(repository.nextId(), description, amount, type);
        repository.save(t);
        return t;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public List<Transaction> search(String keyword) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : repository.findAll()) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Transaction> sort(SortOption option) {
        List<Transaction> sorted = new ArrayList<>(repository.findAll());
        switch (option) {
            case AMOUNT_ASC:
                sorted.sort(Comparator.comparingLong(Transaction::getAmount));
                break;
            case AMOUNT_DESC:
                sorted.sort(Comparator.comparingLong(Transaction::getAmount).reversed());
                break;
            case INCOME_FIRST:
                sorted.sort(Comparator.comparing(Transaction::getType));
                break;
            case EXPENSE_FIRST:
                sorted.sort(Comparator.comparing(Transaction::getType).reversed());
                break;
        }
        return sorted;
    }

    public long getBalance() {
        long balance = 0;
        for (Transaction t : repository.findAll()) {
            balance += (t.getType() == TransactionType.INCOME) ? t.getAmount() : -t.getAmount();
        }
        return balance;
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }
}
