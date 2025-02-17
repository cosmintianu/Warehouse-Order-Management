package presentation;

import business_logic.BillBLL;
import business_logic.ClientBLL;
import business_logic.OrdersBLL;
import business_logic.ProductBLL;
import model.Bill;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class acts as an intermediary between the View and the Business Logic Layer.
 * It handles user interactions and coordinates actions between the view and business logic layers.
 */
public class Controller {
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;
    private BillBLL billBLL;
    private View view;

    /**
     * Constructs a new Controller instance.
     *
     * @param view The view component associated with the controller.
     */
    public Controller(View view) {
        this.view = view;
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.ordersBLL = new OrdersBLL();
        this.billBLL = new BillBLL();
    }

    /**
     * Retrieves all objects<?></?> from the database.
     */
    public Object[][] getTableContent(List<?> list) {
        if (list == null || list.isEmpty()) {
            return new Object[0][0];
        }

        Field[] fields = list.getFirst().getClass().getDeclaredFields();

        Object[][] tableData = new Object[list.size() + 1][fields.length];

        tableData[0] = fields;

        for (int i = 1; i < list.size() + 1; i++) {
            Object obj = list.get(i - 1);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                try {
                    tableData[i][j] = fields[j].get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return tableData;
    }

    /**
     * Updates the client table in the GUI.
     */
    public void showAllClientsInTable() {
        List<Client> clients = clientBLL.findAll();

        DefaultTableModel model = view.getClientTableModel();

        Object[][] aux = getTableContent(clients);

        Field[] fields = (Field[]) aux[0];

        Object[][] tableData= new Object[clients.size()][fields.length];

        for (int i = 1; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                tableData[i-1][j] = aux[i][j];
            }
            System.out.println();
        }

        // Get column names
        if (!clients.isEmpty()) {
            String[] columnNames = new String[fields.length];

            for (int i = 1; i < fields.length; i++) {
                columnNames[i] = fields[i].getName();
            }

            model.setDataVector(tableData, columnNames);
        }
    }

    /**
     * Adds a new client to the database.
     *
     * @param client The client object to be added.
     */
    public void addClient(Client client) {
        clientBLL.insert(client);
        showAllClientsInTable();
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The client object to be updated.
     */
    public void updateClient(Client client){
        clientBLL.update(client);

        showAllClientsInTable();
    }

    /**
     * Deletes a client from the database based on the provided ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void deleteClient(int id){
        clientBLL.delete(id);

        showAllClientsInTable();
    }

    /**
     * Updates the products table in the GUI.
     */
    public void showAllProductsInTable() {
        List<Product> products = productBLL.findAll();

        DefaultTableModel model = view.getProductTableModel();

        Object[][] aux = getTableContent(products);

        Field[] fields = (Field[]) aux[0];

        Object[][] tableData= new Object[products.size()][fields.length];

        for (int i = 1; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                tableData[i-1][j] = aux[i][j];
            }
            System.out.println();
        }


        // Get column names
        if (!products.isEmpty()) {
            String[] columnNames = new String[fields.length];

            for (int i = 1; i < fields.length; i++) {
                columnNames[i] = fields[i].getName();
            }

            model.setDataVector(tableData, columnNames);
        }
    }

    /**
     * Adds a new product to the database.
     *
     * @param product The product object to be added.
     */
    public void addProduct(Product product) {
        productBLL.insert(product);

        showAllProductsInTable();
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product The product object to be updated.
     */
    public void updateProduct(Product product){
        productBLL.update(product);

        showAllProductsInTable();
    }

    /**
     * Deletes a product from the database based on the provided ID.
     *
     * @param id The ID of the product to be deleted.
     */
    public void deleteProduct(int id){
        productBLL.delete(id);

        showAllProductsInTable();
    }

    /**
     * Retrieves the names and IDs of all clients from the database.
     *
     * @return An ArrayList containing two arrays: one for client names and one for client IDs.
     */
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

    /**
     * Retrieves the names and IDs of all products from the database.
     *
     * @return An ArrayList containing two arrays: one for product names and one for product IDs.
     */
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

    /**
     * Adds a new order to the database and updates the orders table in the GUI.
     *
     * @param order The order object to be added.
     * @return True if the order was successfully added, false otherwise.
     */
    public boolean addOrder(Orders order) {
        if(!ordersBLL.addOrder(order)){
            return false;
        }else {
            System.out.println("success");
            showAllOrdersInTable();
            return true;
        }
    }

    /**
     * Retrieves all orders from the database and updates the orders table in the GUI.
     */
    public void showAllOrdersInTable() {
        List<Orders> orders = ordersBLL.findAll();

        DefaultTableModel model = view.getOrderTableModel();
        Field[] fields = Orders.class.getDeclaredFields();

        model.setColumnCount(0);
        for (Field field : fields) {
            model.addColumn(field.getName());
        }
        model.setRowCount(0);
        for (Orders order : orders) {
            Object[] row = {order.getId(), order.getClientId(), order.getProductId(), order.getQuantity()};
            model.addRow(row);
        }
    }

    /**
     * Retrieves all bills from the database and updates the bills table in the GUI.
     */
    public void showAllBillsInTable() {
        List<Bill> bills = billBLL.findAll();

        DefaultTableModel model = view.getBillsTableModel();
        Field[] fields = Bill.class.getDeclaredFields();

        model.setRowCount(0);
        model.setColumnCount(0);

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
