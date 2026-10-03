package usecase;

import domain.entity.Contact;
import domain.entity.SortOption;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactUseCase {
    private final IContactRepository repository;

    public ContactUseCase(IContactRepository repository) {
        this.repository = repository;
    }

    public Contact add(String name, String phone, String email) {
        Contact contact = new Contact(repository.nextId(), name, phone, email);
        repository.save(contact);
        return contact;
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    /**
     * Ubah parsial: field yang kosong (atau null) tidak diubah.
     * Mengembalikan false jika kontak dengan ID tersebut tidak ada.
     */
    public boolean update(int id, String name, String phone, String email) {
        Contact contact = repository.findById(id);
        if (contact == null) {
            return false;
        }
        if (name != null && !name.isEmpty()) {
            contact.setName(name);
        }
        if (phone != null && !phone.isEmpty()) {
            contact.setPhone(phone);
        }
        if (email != null && !email.isEmpty()) {
            contact.setEmail(email);
        }
        return true;
    }

    public List<Contact> search(String keyword) {
        List<Contact> result = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Contact contact : repository.findAll()) {
            if (contact.getName().toLowerCase().contains(key)) {
                result.add(contact);
            }
        }
        return result;
    }

    public List<Contact> sort(SortOption option) {
        List<Contact> sorted = new ArrayList<>(repository.findAll());
        Comparator<Contact> byName = Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER);
        switch (option) {
            case NAME_ASC:
                sorted.sort(byName);
                break;
            case NAME_DESC:
                sorted.sort(byName.reversed());
                break;
        }
        return sorted;
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }
}
