package usecase;

import domain.entity.Item;
import domain.entity.SortOption;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemUseCase {
    private final IItemRepository repository;

    public ItemUseCase(IItemRepository repository) {
        this.repository = repository;
    }

    public Item add(String name, int stock, String category) {
        Item item = new Item(repository.nextId(), name, stock, category);
        repository.save(item);
        return item;
    }

    public List<Item> getAll() {
        return repository.findAll();
    }

    /**
     * Ubah stok secara parsial: jika newStock null, stok tidak diubah.
     * Mengembalikan false jika barang dengan ID tersebut tidak ada.
     */
    public boolean updateStock(int id, Integer newStock) {
        Item item = repository.findById(id);
        if (item == null) {
            return false;
        }
        if (newStock != null) {
            item.setStock(newStock);
        }
        return true;
    }

    public List<Item> search(String keyword) {
        List<Item> result = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Item item : repository.findAll()) {
            if (item.getName().toLowerCase().contains(key)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Item> sort(SortOption option) {
        List<Item> sorted = new ArrayList<>(repository.findAll());
        switch (option) {
            case NAME_ASC:
                sorted.sort(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER));
                break;
            case NAME_DESC:
                sorted.sort(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER).reversed());
                break;
            case STOCK_ASC:
                sorted.sort(Comparator.comparingInt(Item::getStock));
                break;
            case STOCK_DESC:
                sorted.sort(Comparator.comparingInt(Item::getStock).reversed());
                break;
        }
        return sorted;
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }
}
