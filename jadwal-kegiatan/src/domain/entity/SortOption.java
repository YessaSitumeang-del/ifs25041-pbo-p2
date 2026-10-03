package domain.entity;

public enum SortOption {
    DAY_ASC("Hari (Senin -> Minggu)"),
    TIME_ASC("Waktu (Awal -> Akhir)"),
    TITLE_ASC("Judul (A-Z)"),
    TITLE_DESC("Judul (Z-A)");

    private final String description;

    SortOption(String description) { this.description = description; }

    public String getDescription() { return description; }
}
