package domain.entity;

public enum SortOption {
    AMOUNT_ASC("Jumlah (Terkecil)"),
    AMOUNT_DESC("Jumlah (Terbesar)"),
    INCOME_FIRST("Pemasukan Dulu"),
    EXPENSE_FIRST("Pengeluaran Dulu");

    private final String description;

    SortOption(String description) { this.description = description; }

    public String getDescription() { return description; }
}
