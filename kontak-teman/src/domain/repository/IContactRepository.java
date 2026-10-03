package domain.repository;

import domain.entity.Contact;
import java.util.List;

public interface IContactRepository {
    int nextId();
    void save(Contact contact);
    List<Contact> findAll();
    Contact findById(int id);
    boolean deleteById(int id);
}
