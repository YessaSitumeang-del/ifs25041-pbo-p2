package adapter.presenter;

import domain.entity.Item;
import java.util.List;

public class ItemPresenter {

    public String item(Item i) {
        return i.getId() + " | " + i.getName() + " | " + i.getStock() + " | " + i.getCategory();
    }

    public String list(String title, List<Item> items) {
        StringBuilder sb = new StringBuilder(title);
        if (items.isEmpty()) {
            sb.append("\n- Data barang belum tersedia!");
        } else {
            for (Item i : items) {
                sb.append("\n").append(item(i));
            }
        }
        return sb.toString();
    }

    public String searchResult(String keyword, List<Item> items) {
        StringBuilder sb = new StringBuilder("Hasil Pencarian: \"" + keyword + "\"");
        if (items.isEmpty()) {
            sb.append("\n- Barang tidak ditemukan!");
        } else {
            for (Item i : items) {
                sb.append("\n").append(item(i));
            }
        }
        return sb.toString();
    }

    public String added(Item i) { return "Berhasil menambah barang: " + item(i); }
}
