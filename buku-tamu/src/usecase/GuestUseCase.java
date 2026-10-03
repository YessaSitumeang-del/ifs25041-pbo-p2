package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Logika bisnis buku tamu (tanpa I/O).
 */
public class GuestUseCase {
    private final IGuestRepository repository;

    public GuestUseCase(IGuestRepository repository) {
        this.repository = repository;
    }

    /** Mendaftarkan tamu baru. Nama dan tujuan tidak boleh kosong. */
    public Guest daftarkan(String name, String purpose) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new IllegalArgumentException("Tujuan kunjungan tidak boleh kosong.");
        }
        Guest guest = new Guest(repository.nextId(), name.trim(), purpose.trim());
        repository.save(guest);
        return guest;
    }

    /** Mencari tamu berdasarkan nama (case-insensitive, sebagian nama cocok). */
    public List<Guest> cari(String keyword) {
        List<Guest> hasil = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return hasil;
        }
        String key = keyword.trim().toLowerCase();
        for (Guest guest : repository.findAll()) {
            if (guest.getName().toLowerCase().contains(key)) {
                hasil.add(guest);
            }
        }
        return hasil;
    }

    /** Menghapus tamu berdasarkan id. */
    public boolean hapus(int id) {
        return repository.deleteById(id);
    }

    /** Mengambil semua tamu. */
    public List<Guest> semua() {
        return repository.findAll();
    }
}
