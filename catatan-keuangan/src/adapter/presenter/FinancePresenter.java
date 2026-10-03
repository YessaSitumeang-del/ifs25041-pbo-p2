package adapter.presenter;

import domain.entity.Transaction;
import java.util.List;

public class FinancePresenter {

    public String transaction(Transaction t) {
        return t.getId() + " | " + t.getDescription() + " | Rp " + t.getAmount()
                + " | " + t.getType().getLabel();
    }

    public String list(String title, List<Transaction> list) {
        StringBuilder sb = new StringBuilder(title);
        if (list.isEmpty()) {
            sb.append("\n- Belum ada transaksi!");
        } else {
            for (Transaction t : list) {
                sb.append("\n").append(transaction(t));
            }
        }
        return sb.toString();
    }

    public String searchResult(String keyword, List<Transaction> list) {
        StringBuilder sb = new StringBuilder("Hasil Pencarian: \"" + keyword + "\"");
        if (list.isEmpty()) {
            sb.append("\n- Transaksi tidak ditemukan!");
        } else {
            for (Transaction t : list) {
                sb.append("\n").append(transaction(t));
            }
        }
        return sb.toString();
    }

    public String balance(long value) { return "Saldo: Rp " + value; }

    public String currentBalance(long value) { return "Saldo saat ini: Rp " + value; }

    public String added(Transaction t) { return "Berhasil menambah transaksi: " + transaction(t); }
}
