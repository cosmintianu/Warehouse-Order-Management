package presentation;

import data_access.ClientDAO;
import data_access.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private View view;

    public Controller(View view) {
        this.view = view;
        this.clientDAO = new ClientDAO(); // Instantiate ClientDAO
        this.productDAO = new ProductDAO();
    }

    // Method to fetch all clients from the database and update the JTable
    public void showAllClientsInTable() {
        // Fetch all clients from the database using ClientDAO
        List<Client> clients = clientDAO.findAll();

        // Update the JTable in the GUI
        DefaultTableModel model = view.getClientTableModel();
        model.setRowCount(0); // Clear existing rows
        for (Client client : clients) {
            Object[] row = {client.getId(), client.getName(), client.getEmail(), client.getAge()};
            model.addRow(row);
        }
    }

    public void addClient(Client client) {
        clientDAO.insert(client);
        // Optionally, you can refresh the client table in the GUI after adding the client
        showAllClientsInTable();
    }

    public void updateClient(Client client){
        clientDAO.update(client);

        showAllClientsInTable();
    }

    public void deleteClient(int id){
        clientDAO.delete(id);

        showAllClientsInTable();
    }

    public void showAllProductsInTable() {
        // Fetch all clients from the database using ClientDAO
        List<Product> products = productDAO.findAll();

        // Update the JTable in the GUI
        DefaultTableModel model = view.getProductTableModel();
        model.setRowCount(0); // Clear existing rows
        for (Product product : products) {
            Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getStock()};
            model.addRow(row);
        }
    }

    public void addProduct(Product product) {
        productDAO.insert(product);

        showAllProductsInTable();
    }

    public void updateProduct(Product product){
        productDAO.update(product);

        showAllProductsInTable();
    }

    public void deleteProduct(int id){
        productDAO.delete(id);

        showAllProductsInTable();
    }

}
