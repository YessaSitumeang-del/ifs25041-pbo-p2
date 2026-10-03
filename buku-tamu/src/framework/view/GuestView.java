package framework.view;

import adapter.presenter.GuestPresenter;
import domain.entity.Guest;
import framework.util.InputUtil;
import java.util.List;
import usecase.GuestUseCase;

/**
 * UI konsol + menu buku tamu.
 * Mengetik "x" di menu = keluar; mengetik "x" di prompt lain = batalkan operasi.
 */
public class GuestView {
    private final GuestUseCase useCase;
    private final GuestPresenter presenter;

    public GuestView(GuestUseCase useCase, GuestPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            System.out.println(presenter.presentList(useCase.semua()));
            System.out.println(presenter.presentMenu());
            String choice = InputUtil.readLine("Pilih : ");
            if (choice == null || InputUtil.isExit(choice)) {
                return;
            }
            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    search();
                    break;
                case "3":
                    delete();
                    break;
                default:
                    System.out.println(presenter.presentInvalidMenu());
                    System.out.println();
            }
        }
    }

    private void register() {
        System.out.println("[Mendaftarkan Tamu]");
        String name = InputUtil.readLine("Nama (x Jika Batal) : ");
        if (name == null || InputUtil.isExit(name)) {
            System.out.println();
            return;
        }
        String purpose = InputUtil.readLine("Tujuan Kunjungan (x Jika Batal) : ");
        if (purpose == null || InputUtil.isExit(purpose)) {
            System.out.println();
            return;
        }
        try {
            Guest guest = useCase.daftarkan(name, purpose);
            System.out.println(presenter.presentRegistered(guest));
        } catch (IllegalArgumentException e) {
            System.out.println(presenter.presentError(e.getMessage()));
        }
        System.out.println();
    }

    private void search() {
        System.out.println("[Mencari Tamu]");
        String keyword = InputUtil.readLine("Nama (x Jika Batal) : ");
        if (keyword == null || InputUtil.isExit(keyword)) {
            System.out.println();
            return;
        }
        List<Guest> hasil = useCase.cari(keyword);
        System.out.println(presenter.presentSearchResult(keyword, hasil));
        System.out.println();
    }

    private void delete() {
        System.out.println("[Menghapus Tamu]");
        String input = InputUtil.readLine("[ID Tamu] yang dihapus (x Jika Batal) : ");
        if (input == null || InputUtil.isExit(input)) {
            System.out.println();
            return;
        }
        try {
            int id = Integer.parseInt(input);
            if (useCase.hapus(id)) {
                System.out.println(presenter.presentDeleted());
            } else {
                System.out.println(presenter.presentDeleteFailed(id));
            }
        } catch (NumberFormatException e) {
            System.out.println(presenter.presentInvalidId());
        }
        System.out.println();
    }
}
