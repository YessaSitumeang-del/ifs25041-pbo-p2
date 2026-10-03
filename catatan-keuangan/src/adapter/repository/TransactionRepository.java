package adapter.repository;

import domain.entity.Transaction;
import domain.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int nextId() { return nextId++; }

    @Override
    public void save(Transaction transaction) { data.add(transaction); }

    @Override
    public List<Transaction> findAll() { return new ArrayList<>(data); }

    @Override
    public boolean deleteById(int id) { return data.removeIf(t -> t.getId() == id); }
}
