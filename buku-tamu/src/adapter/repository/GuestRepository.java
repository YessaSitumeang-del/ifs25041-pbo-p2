package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementasi penyimpanan in-memory.
 */
public class GuestRepository implements IGuestRepository {
    private final List<Guest> storage = new ArrayList<>();
    private int counter = 0;

    @Override
    public int nextId() {
        return ++counter;
    }

    @Override
    public void save(Guest guest) {
        storage.add(guest);
    }

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public boolean deleteById(int id) {
        Iterator<Guest> it = storage.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
