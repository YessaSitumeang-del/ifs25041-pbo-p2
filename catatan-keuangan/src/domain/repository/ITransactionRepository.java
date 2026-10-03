package domain.repository;

import domain.entity.Transaction;
import java.util.List;

public interface ITransactionRepository {
    int nextId();
    void save(Transaction transaction);
    List<Transaction> findAll();
    boolean deleteById(int id);
}
