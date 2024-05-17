package business_logic;

import data_access.ProductDAO;
import model.Product;

import java.util.List;

/**
 * Provides business logic operations related to products.
 */
public class ProductBLL {
    private final ProductDAO productDAO;

    /**
     * Constructs a new ProductBLL instance.
     */
    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Retrieves a list of all products.
     *
     * @return A list of Product objects representing the products.
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product The Product object to be inserted.
     */
    public void insert(Product product) {
        productDAO.insert(product);
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product The Product object to be updated.
     */
    public void update(Product product) {
        productDAO.update(product);
    }

    /**
     * Deletes a product from the database based on its ID.
     *
     * @param id The ID of the product to be deleted.
     */
    public void delete(int id) {
        productDAO.delete(id);
    }
}
