package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements IItemRepository {
    private final List<Item> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int nextId() { return nextId++; }

    @Override
    public void save(Item item) { data.add(item); }

    @Override
    public List<Item> findAll() { return new ArrayList<>(data); }

    @Override
    public Item findById(int id) {
        for (Item item : data) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) { return data.removeIf(i -> i.getId() == id); }
}
