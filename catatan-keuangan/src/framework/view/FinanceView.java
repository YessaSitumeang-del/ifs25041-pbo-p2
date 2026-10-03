package framework.view;

import adapter.presenter.FinancePresenter;
import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import framework.util.InputUtil;
import java.util.List;
import usecase.FinanceUseCase;

public class FinanceView {
    private final FinanceUseCase useCase;
    private final FinancePresenter presenter;
    private final InputUtil input = new InputUtil();

    public FinanceView(FinanceUseCase useCase, FinancePresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            System.out.println(presenter.list("Daftar Transaksi:", useCase.getAll()));
            System.out.println(presenter.balance(useCase.getBalance()));
            printMenu();

            String choice = input.readLine("Pilih : ");
            if (choice.equalsIgnoreCase("x")) {
                break;
            }

            switch (choice) {
                case "1":
                    System.out.println("[Tambah Pemasukan]");
                    add(TransactionType.INCOME);
                    break;
                case "2":
                    System.out.println("[Tambah Pengeluaran]");
                    add(TransactionType.EXPENSE);
                    break;
                case "3":
                    System.out.println("[Cari Transaksi]");
                    search();
                    break;
                case "4":
                    System.out.println("[Urutkan Transaksi]");
                    sort();
                    break;
                case "5":
                    System.out.println(presenter.currentBalance(useCase.getBalance()));
                    break;
                case "6":
                    System.out.println("[Hapus Transaksi]");
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
        System.out.println("1. Tambah Pemasukan");
        System.out.println("2. Tambah Pengeluaran");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Lihat Saldo");
        System.out.println("6. Hapus");
        System.out.println("x. Keluar");
    }

    private void add(TransactionType type) {
        String description = input.readLine("Keterangan (x Jika Batal) : ");
        if (description.equalsIgnoreCase("x")) {
            return;
        }

        String amountInput = input.readLine("Jumlah : ");
        try {
            long amount = Long.parseLong(amountInput);
            Transaction t = useCase.add(description, amount, type);
            System.out.println(presenter.added(t));
        } catch (IllegalArgumentException e) {
            // NumberFormatException adalah turunan IllegalArgumentException,
            // jadi angka non-numerik dan jumlah <= 0 sama-sama masuk sini.
            System.out.println("[!] Jumlah tidak valid!");
        }
    }

    private void search() {
        String keyword = input.readLine("Kata Kunci (x Jika Batal) : ");
        if (keyword.equalsIgnoreCase("x")) {
            return;
        }
        List<Transaction> result = useCase.search(keyword);
        System.out.println(presenter.searchResult(keyword, result));
    }

    private void sort() {
        SortOption[] options = SortOption.values();
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

        List<Transaction> sorted = useCase.sort(options[index]);
        System.out.println(presenter.list("Daftar Transaksi (Terurut):", sorted));
    }

    private void delete() {
        String idInput = input.readLine("ID Transaksi (x Jika Batal) : ");
        if (idInput.equalsIgnoreCase("x")) {
            return;
        }
        try {
            int id = Integer.parseInt(idInput);
            if (useCase.delete(id)) {
                System.out.println("Berhasil menghapus transaksi.");
            } else {
                System.out.println("[!] Gagal menghapus transaksi dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
        }
    }
}
