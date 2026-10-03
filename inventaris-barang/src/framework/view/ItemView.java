package framework.view;

import adapter.presenter.ItemPresenter;
import domain.entity.Item;
import domain.entity.SortOption;
import framework.util.InputUtil;
import java.util.List;
import usecase.ItemUseCase;

public class ItemView {
    private final ItemUseCase useCase;
    private final ItemPresenter presenter;
    private final InputUtil input = new InputUtil();

    public ItemView(ItemUseCase useCase, ItemPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            System.out.println(presenter.list("Daftar Barang:", useCase.getAll()));
            printMenu();

            String choice = input.readLine("Pilih : ");
            if (choice.equalsIgnoreCase("x")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.println("[Menambah Barang]");
                    add();
                    break;
                case "2":
                    System.out.println("[Mengubah Stok]");
                    updateStock();
                    break;
                case "3":
                    System.out.println("[Mencari Barang]");
                    search();
                    break;
                case "4":
                    System.out.println("[Mengurutkan Barang]");
                    sort();
                    break;
                case "5":
                    System.out.println("[Menghapus Barang]");
                    delete();
                    break;
                default:
                    System.out.println("[!] Pilihan tidak dimengerti.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah Stok");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    // Stok harus numerik dan > 0. Mengembalikan null jika tidak valid.
    private Integer parseStock(String text) {
        try {
            int value = Integer.parseInt(text);
            return value > 0 ? value : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void add() {
        String name = input.readLine("Nama (x Jika Batal) : ");
        if (name.equalsIgnoreCase("x")) {
            return;
        }

        Integer stock = parseStock(input.readLine("Jumlah : "));
        if (stock == null) {
            System.out.println("[!] Jumlah stok tidak valid!");
            return;
        }

        String category = input.readLine("Kategori (x Jika Batal) : ");
        if (category.equalsIgnoreCase("x")) {
            return;
        }

        Item item = useCase.add(name, stock, category);
        System.out.println(presenter.added(item));
    }

    private void updateStock() {
        String idInput = input.readLine("ID Barang yang diubah (x Jika Batal) : ");
        if (idInput.equalsIgnoreCase("x")) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
            return;
        }

        String stockInput = input.readLine("Jumlah Baru (Kosongkan jika tidak ingin mengubah) : ");
        Integer newStock = null;
        if (!stockInput.isEmpty()) {
            newStock = parseStock(stockInput);
            if (newStock == null) {
                System.out.println("[!] Jumlah stok tidak valid!");
                return;
            }
        }

        if (useCase.updateStock(id, newStock)) {
            System.out.println("Berhasil mengubah stok barang.");
        } else {
            System.out.println("[!] Gagal mengubah stok barang dengan ID: " + id + ".");
        }
    }

    private void search() {
        String keyword = input.readLine("Kata Kunci (x Jika Batal) : ");
        if (keyword.equalsIgnoreCase("x")) {
            return;
        }
        System.out.println(presenter.searchResult(keyword, useCase.search(keyword)));
    }

    private void sort() {
        SortOption[] options = SortOption.values();
        System.out.println("Pilihan Pengurutan:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i].getDescription());
        }
        System.out.println("x. Batal");

        String choice = input.readLine("Pilih : ");
        if (choice.equalsIgnoreCase("x")) {
            return;
        }

        int index;
        try {
            index = Integer.parseInt(choice) - 1;
        } catch (NumberFormatException e) {
            index = -1;
        }
        if (index < 0 || index >= options.length) {
            System.out.println("[!] Pilihan tidak valid!");
            return;
        }

        List<Item> sorted = useCase.sort(options[index]);
        System.out.println(presenter.list("Daftar Barang (Terurut):", sorted));
    }

    private void delete() {
        String idInput = input.readLine("[ID Barang] yang dihapus (x Jika Batal) : ");
        if (idInput.equalsIgnoreCase("x")) {
            return;
        }
        try {
            int id = Integer.parseInt(idInput);
            if (useCase.delete(id)) {
                System.out.println("Berhasil menghapus barang.");
            } else {
                System.out.println("[!] Gagal menghapus barang dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
        }
    }
}
