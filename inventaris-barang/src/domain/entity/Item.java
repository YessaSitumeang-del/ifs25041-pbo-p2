package domain.entity;

public class Item {
    private final int id;
    private final String name;
    private int stock;
    private final String category;

    public Item(int id, String name, int stock, String category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }

    public void setStock(int stock) { this.stock = stock; }
}
