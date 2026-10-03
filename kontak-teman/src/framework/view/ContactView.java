package framework.view;

import adapter.presenter.ContactPresenter;
import domain.entity.Contact;
import domain.entity.SortOption;
import framework.util.InputUtil;
import java.util.List;
import usecase.ContactUseCase;

public class ContactView {
    private final ContactUseCase useCase;
    private final ContactPresenter presenter;
    private final InputUtil input = new InputUtil();

    public ContactView(ContactUseCase useCase, ContactPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            System.out.println(presenter.list("Daftar Kontak:", useCase.getAll()));
            printMenu();

            String choice = input.readLine("Pilih : ");
            if (choice.equalsIgnoreCase("x")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.println("[Menambah Kontak]");
                    add();
                    break;
                case "2":
                    System.out.println("[Mengubah Kontak]");
                    update();
                    break;
                case "3":
                    System.out.println("[Mencari Kontak]");
                    search();
                    break;
                case "4":
                    System.out.println("[Mengurutkan Kontak]");
                    sort();
                    break;
                case "5":
                    System.out.println("[Menghapus Kontak]");
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
        System.out.println("2. Ubah");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    private void add() {
        String name = input.readLine("Nama (x Jika Batal) : ");
        if (name.equalsIgnoreCase("x")) {
            return;
        }
        String phone = input.readLine("Telepon : ");
        String email = input.readLine("Email : ");

        Contact contact = useCase.add(name, phone, email);
        System.out.println(presenter.added(contact));
    }

    private void update() {
        String idInput = input.readLine("ID Kontak yang diubah (x Jika Batal) : ");
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

        String name = input.readLine("Nama Baru (Kosongkan jika tidak ingin mengubah) : ");
        String phone = input.readLine("Telepon Baru (Kosongkan jika tidak ingin mengubah) : ");
        String email = input.readLine("Email Baru (Kosongkan jika tidak ingin mengubah) : ");

        if (useCase.update(id, name, phone, email)) {
            System.out.println("Berhasil mengubah kontak.");
        } else {
            System.out.println("[!] Gagal mengubah kontak dengan ID: " + id + ".");
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

        List<Contact> sorted = useCase.sort(options[index]);
        System.out.println(presenter.list("Daftar Kontak (Terurut):", sorted));
    }

    private void delete() {
        String idInput = input.readLine("[ID Kontak] yang dihapus (x Jika Batal) : ");
        if (idInput.equalsIgnoreCase("x")) {
            return;
        }
        try {
            int id = Integer.parseInt(idInput);
            if (useCase.delete(id)) {
                System.out.println("Berhasil menghapus kontak.");
            } else {
                System.out.println("[!] Gagal menghapus kontak dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
        }
    }
}
