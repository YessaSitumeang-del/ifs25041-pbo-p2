package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

public class ContactPresenter {

    public String contact(Contact c) {
        return c.getId() + " | " + c.getName() + " | " + c.getPhone() + " | " + c.getEmail();
    }

    public String list(String title, List<Contact> contacts) {
        StringBuilder sb = new StringBuilder(title);
        if (contacts.isEmpty()) {
            sb.append("\n- Data kontak belum tersedia!");
        } else {
            for (Contact c : contacts) {
                sb.append("\n").append(contact(c));
            }
        }
        return sb.toString();
    }

    public String searchResult(String keyword, List<Contact> contacts) {
        StringBuilder sb = new StringBuilder("Hasil Pencarian: \"" + keyword + "\"");
        if (contacts.isEmpty()) {
            sb.append("\n- Kontak tidak ditemukan!");
        } else {
            for (Contact c : contacts) {
                sb.append("\n").append(contact(c));
            }
        }
        return sb.toString();
    }

    public String added(Contact c) { return "Berhasil menambah kontak: " + contact(c); }
}
