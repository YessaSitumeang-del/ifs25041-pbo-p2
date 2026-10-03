package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

/**
 * Memformat output ke layar. Format baris tamu: <id> | <nama> | <tujuan kunjungan>
 */
public class GuestPresenter {

    public String formatGuest(Guest guest) {
        return guest.getId() + " | " + guest.getName() + " | " + guest.getPurpose();
    }

    public String presentList(List<Guest> guests) {
        StringBuilder sb = new StringBuilder("Daftar Tamu:");
        if (guests.isEmpty()) {
            sb.append("\n- Data tamu belum tersedia!");
        }
        for (Guest guest : guests) {
            sb.append("\n").append(formatGuest(guest));
        }
        return sb.toString();
    }

    public String presentMenu() {
        return "Menu:\n"
                + "1. Daftarkan\n"
                + "2. Cari\n"
                + "3. Hapus\n"
                + "x. Keluar";
    }

    public String presentRegistered(Guest guest) {
        return "Berhasil mendaftarkan tamu: " + formatGuest(guest);
    }

    public String presentSearchResult(String keyword, List<Guest> guests) {
        StringBuilder sb = new StringBuilder("Hasil Pencarian: \"" + keyword + "\"");
        if (guests.isEmpty()) {
            sb.append("\n- Tamu tidak ditemukan!");
        }
        for (Guest guest : guests) {
            sb.append("\n").append(formatGuest(guest));
        }
        return sb.toString();
    }

    public String presentDeleted() {
        return "Berhasil menghapus tamu.";
    }

    public String presentDeleteFailed(int id) {
        return "[!] Gagal menghapus tamu dengan ID: " + id + ".";
    }

    public String presentInvalidId() {
        return "[!] ID tidak valid!";
    }

    public String presentInvalidMenu() {
        return "[!] Pilihan tidak dimengerti.";
    }

    public String presentError(String message) {
        return "[!] " + message;
    }
}
