package domain.entity;

public enum SortOption {
    NAME_ASC("Nama (A-Z)"),
    NAME_DESC("Nama (Z-A)");

    private final String description;

    SortOption(String description) { this.description = description; }

    public String getDescription() { return description; }
}
