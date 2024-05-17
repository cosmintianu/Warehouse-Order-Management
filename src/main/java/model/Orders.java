package model;

/**
 * Represents an order in the system.
 */
public class Orders {
    int id;
    int clientId;
    int productId;
    int quantity;

    /**
     * Constructs a new order with the specified client ID, product ID, and quantity.
     *
     * @param clientId  The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param quantity  The quantity of the product being ordered.
     */
    public Orders(int clientId, int productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Constructs a new order with the specified ID, client ID, product ID, and quantity.
     *
     * @param id        The unique identifier of the order.
     * @param clientId  The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param quantity  The quantity of the product being ordered.
     */
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Constructs a new order with default values.
     */
    public Orders() {
    }

    /**
     * Retrieves the unique identifier of the order.
     *
     * @return The order's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the order.
     *
     * @param id The order's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the client placing the order.
     *
     * @return The client's ID.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client placing the order.
     *
     * @param clientId The client's ID.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the ID of the product being ordered.
     *
     * @return The product's ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product being ordered.
     *
     * @param productId The product's ID.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Retrieves the quantity of the product being ordered.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product being ordered.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
