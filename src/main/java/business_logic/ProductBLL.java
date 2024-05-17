package business_logic;

import data_access.ClientDAO;
import data_access.ProductDAO;
import model.Client;
import model.Product;

import java.util.List;

public class ProductBLL {
    ProductDAO productDAO;

    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> findAll() {
        // Fetch all clients from the database using ClientDAO
        return productDAO.findAll();
    }

    public void insert(Product product) {
        productDAO.insert(product);
    }

    public void update(Product product){
        productDAO.update(product);
    }

    public void delete(int id){
        productDAO.delete(id);
    }
}
