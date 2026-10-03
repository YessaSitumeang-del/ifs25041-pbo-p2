package domain.entity;

public enum SortOption {
    NAME_ASC("Nama (A-Z)"),
    NAME_DESC("Nama (Z-A)"),
    STOCK_ASC("Jumlah (Terkecil -> Terbesar)"),
    STOCK_DESC("Jumlah (Terbesar -> Terkecil)");

    private final String description;

    SortOption(String description) { this.description = description; }

    public String getDescription() { return description; }
}
