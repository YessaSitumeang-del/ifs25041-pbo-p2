package adapter.repository;

import domain.entity.Contact;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository implements IContactRepository {
    private final List<Contact> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int nextId() { return nextId++; }

    @Override
    public void save(Contact contact) { data.add(contact); }

    @Override
    public List<Contact> findAll() { return new ArrayList<>(data); }

    @Override
    public Contact findById(int id) {
        for (Contact contact : data) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) { return data.removeIf(c -> c.getId() == id); }
}
