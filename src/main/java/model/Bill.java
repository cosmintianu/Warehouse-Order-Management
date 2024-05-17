package model;

/**
 * Represents a bill in the system.
 */
public record Bill(int id, int orderId, double total) {

    /**
     * Constructs a new bill with default values.
     */
    public Bill() {
        this(0, 0, 0);
    }

    /**
     * Constructs a new bill with the specified order ID and total.
     *
     * @param orderId The ID of the associated order.
     * @param total   The total amount of the bill.
     */
    public Bill(int orderId, double total) {
        this(0, orderId, total);
    }
}

