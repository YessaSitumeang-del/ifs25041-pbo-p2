package domain.repository;

import domain.entity.Item;
import java.util.List;

public interface IItemRepository {
    int nextId();
    void save(Item item);
    List<Item> findAll();
    Item findById(int id);
    boolean deleteById(int id);
}
