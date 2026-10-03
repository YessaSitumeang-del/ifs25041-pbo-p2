package domain.repository;

import domain.entity.Guest;
import java.util.List;

/**
 * Port (kontrak data) untuk penyimpanan tamu.
 */
public interface IGuestRepository {
    /** Menghasilkan id berikutnya yang unik. */
    int nextId();

    /** Menyimpan tamu baru. */
    void save(Guest guest);

    /** Mengambil seluruh tamu sesuai urutan pendaftaran. */
    List<Guest> findAll();

    /** Menghapus tamu berdasarkan id; true jika ada yang terhapus. */
    boolean deleteById(int id);
}
