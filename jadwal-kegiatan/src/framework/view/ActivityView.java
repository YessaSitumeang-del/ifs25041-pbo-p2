package framework.view;

import adapter.presenter.ActivityPresenter;
import domain.entity.Activity;
import domain.entity.SortOption;
import framework.util.InputUtil;
import java.util.List;
import usecase.ActivityUseCase;

public class ActivityView {
    private final ActivityUseCase useCase;
    private final ActivityPresenter presenter;
    private final InputUtil input = new InputUtil();

    public ActivityView(ActivityUseCase useCase, ActivityPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            System.out.println(presenter.list("Daftar Kegiatan:", useCase.getAll()));
            printMenu();

            String choice = input.readLine("Pilih : ");
            if (choice.equalsIgnoreCase("x")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.println("[Menambah Kegiatan]");
                    add();
                    break;
                case "2":
                    System.out.println("[Mengubah Kegiatan]");
                    update();
                    break;
                case "3":
                    System.out.println("[Mencari Kegiatan]");
                    search();
                    break;
                case "4":
                    System.out.println("[Mengurutkan Kegiatan]");
                    sort();
                    break;
                case "5":
                    System.out.println("[Menghapus Kegiatan]");
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
        String title = input.readLine("Judul (x Jika Batal) : ");
        if (title.equalsIgnoreCase("x")) {
            return;
        }
        String day = input.readLine("Hari (x Jika Batal) : ");
        if (day.equalsIgnoreCase("x")) {
            return;
        }
        String time = input.readLine("Waktu (x Jika Batal) : ");
        if (time.equalsIgnoreCase("x")) {
            return;
        }

        Activity activity = useCase.add(title, day, time);
        System.out.println(presenter.added(activity));
    }

    private void update() {
        String idInput = input.readLine("ID Kegiatan yang diubah (x Jika Batal) : ");
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

        String title = input.readLine("Judul Baru (Kosongkan jika tidak ingin mengubah) : ");
        String day = input.readLine("Hari Baru (Kosongkan jika tidak ingin mengubah) : ");
        String time = input.readLine("Waktu Baru (Kosongkan jika tidak ingin mengubah) : ");

        if (useCase.update(id, title, day, time)) {
            System.out.println("Berhasil mengubah kegiatan.");
        } else {
            System.out.println("[!] Gagal mengubah kegiatan dengan ID: " + id + ".");
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

        List<Activity> sorted = useCase.sort(options[index]);
        System.out.println(presenter.list("Daftar Kegiatan (Terurut):", sorted));
    }

    private void delete() {
        String idInput = input.readLine("[ID Kegiatan] yang dihapus (x Jika Batal) : ");
        if (idInput.equalsIgnoreCase("x")) {
            return;
        }
        try {
            int id = Integer.parseInt(idInput);
            if (useCase.delete(id)) {
                System.out.println("Berhasil menghapus kegiatan.");
            } else {
                System.out.println("[!] Gagal menghapus kegiatan dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
        }
    }
}
