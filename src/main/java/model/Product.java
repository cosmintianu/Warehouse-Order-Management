package model;

/**
 * Represents a product in the system.
 */
public class Product {
    int id;
    String name;
    double price;
    int stock;

    /**
     * Constructs a new product with default values.
     */
    public Product() {}

    /**
     * Constructs a new product with the specified name, price, and stock.
     *
     * @param name  The name of the product.
     * @param price The price of the product.
     * @param stock The available stock of the product.
     */
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs a new product with the specified ID, name, price, and stock.
     *
     * @param id    The unique identifier of the product.
     * @param name  The name of the product.
     * @param price The price of the product.
     * @param stock The available stock of the product.
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Retrieves the unique identifier of the product.
     *
     * @return The product's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param id The product's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The product's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The product's price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the available stock of the product.
     *
     * @return The product's stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the available stock of the product.
     *
     * @param stock The product's stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
