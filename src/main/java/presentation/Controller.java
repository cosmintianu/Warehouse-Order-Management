package presentation;

import business_logic.BillBLL;
import business_logic.ClientBLL;
import business_logic.OrdersBLL;
import business_logic.ProductBLL;
import data_access.ClientDAO;
import data_access.OrderDAO;
import data_access.ProductDAO;
import model.Bill;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;
    private BillBLL billBLL;
    private View view;

    public Controller(View view) {
        this.view = view;
        this.clientBLL = new ClientBLL(); // Instantiate ClientDAO
        this.productBLL = new ProductBLL();
        this.ordersBLL = new OrdersBLL();
        this.billBLL = new BillBLL();
    }

    // Method to fetch all clients from the database and update the JTable
    public void showAllClientsInTable() {
        // Fetch all clients from the database using ClientDAO
        List<Client> clients = clientBLL.findAll();

        // Update the JTable in the GUI
        DefaultTableModel model = view.getClientTableModel();
        Field[] fields = Client.class.getDeclaredFields();

        // Set the columns based on the fields
        model.setColumnCount(0); // Clear existing columns
        for (Field field : fields) {
            model.addColumn(field.getName());
        }
        model.setRowCount(0); // Clear existing rows
        for (Client client : clients) {
            Object[] row = {client.getId(), client.getName(), client.getEmail(), client.getAge()};
            model.addRow(row);
        }
    }

    public void addClient(Client client) {
        clientBLL.insert(client);
        // Optionally, you can refresh the client table in the GUI after adding the client
        showAllClientsInTable();
    }

    public void updateClient(Client client){
        clientBLL.update(client);

        showAllClientsInTable();
    }

    public void deleteClient(int id){
        clientBLL.delete(id);

        showAllClientsInTable();
    }

    public void showAllProductsInTable() {
        // Fetch all clients from the database using ClientDAO
        List<Product> products = productBLL.findAll();

        // Update the JTable in the GUI
        DefaultTableModel model = view.getProductTableModel();
        Field[] fields = Product.class.getDeclaredFields();

        // Set the columns based on the fields
        model.setColumnCount(0); // Clear existing columns
        for (Field field : fields) {
            model.addColumn(field.getName());
        }
        model.setRowCount(0); // Clear existing rows

        for (Product product : products) {
            Object[] row = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // Make private fields accessible
                try {
                    row[i] = fields[i].get(product);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(row);
        }
    }

    public void addProduct(Product product) {
        productBLL.insert(product);

        showAllProductsInTable();
    }

    public void updateProduct(Product product){
        productBLL.update(product);

        showAllProductsInTable();
    }

    public void deleteProduct(int id){
        productBLL.delete(id);

        showAllProductsInTable();
    }

    public ArrayList<String[]> getClientsNameAndId() {
        // Fetch all clients from the database using ClientDAO
        List<Client> clients = clientBLL.findAll();
        String[] clientNames = new String[clients.size()];
        String[] clientIds = new String[clients.size()];
        int i = 0;

        for (Client client : clients) {
            clientNames[i] = client.getName();
            clientIds[i++] = String.valueOf(client.getId());
        }
        ArrayList<String[]> result = new ArrayList<>();
        result.add(clientNames);
        result.add(clientIds);

        return result;
    }
    public ArrayList<String[]> getProductsNameAndId() {
        // Fetch all clients from the database using ClientDAO
        List<Product> products = productBLL.findAll();
        String[] productNames = new String[products.size()];
        String[] productIds = new String[products.size()];
        int i = 0;

        for (Product product : products) {
            productNames[i] = product.getName();
            productIds[i++] = String.valueOf(product.getId());
        }
        ArrayList<String[]> result = new ArrayList<>();
        result.add(productNames);
        result.add(productIds);

        return result;
    }

    public boolean addOrder(Orders order) {
        if(!ordersBLL.addOrder(order)){
            return false;
        }else {
            System.out.println("success");
            showAllOrdersInTable();
            return true;
        }
    }

    public void showAllOrdersInTable() {
        // Fetch all clients from the database using ClientDAO
        List<Orders> orders = ordersBLL.findAll();

        // Update the JTable in the GUI
        DefaultTableModel model = view.getOrderTableModel();
        Field[] fields = Orders.class.getDeclaredFields();

        // Set the columns based on the fields
        model.setColumnCount(0); // Clear existing columns
        for (Field field : fields) {
            model.addColumn(field.getName());
        }
        model.setRowCount(0); // Clear existing rows
        for (Orders order : orders) {
            Object[] row = {order.getId(), order.getClientId(), order.getProductId(), order.getQuantity()};
            model.addRow(row);
        }
    }

    public void showAllBillsInTable() {
        List<Bill> bills = billBLL.findAll();

        DefaultTableModel model = view.getBillsTableModel();
        Field[] fields = Bill.class.getDeclaredFields();

        model.setRowCount(0);
        model.setColumnCount(0);

        // Add columns to the table model based on fields
        for (Field field : fields) {
            model.addColumn(field.getName());
        }

        // Populate the table model with bill data
        for (Bill bill : bills) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    rowData[i] = fields[i].get(bill);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(rowData);
        }
    }
}
