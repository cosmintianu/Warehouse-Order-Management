package presentation;

import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View {
    private Controller controller;
    private JFrame mainFrame;
    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel orderTableModel;
    private DefaultTableModel billsTableModel;

    /**
     * Constructs a new View instance.
     */
    public View() {
        controller = new Controller(this);
        mainFrame = new JFrame("Inventory Management System");
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        // Add buttons for client operations and product operations
        JButton clientButton = new JButton("Client Operations");
        JButton productButton = new JButton("Product Operations");
        JButton orderButton = new JButton("Order Operations");
        JButton billButton = new JButton("View Bills");

        // Add action listeners for buttons
        clientButton.addActionListener(e -> showClientOperationsWindow());

        productButton.addActionListener(e -> showProductOperationsWindow());

        orderButton.addActionListener(e -> showOrderOperationsWindow());

        billButton.addActionListener(e -> showBillsWindow());

        mainFrame.add(clientButton);
        mainFrame.add(productButton);
        mainFrame.add(orderButton);
        mainFrame.add(billButton);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /**
     * Displays the window for viewing bills.
     */
    private void showBillsWindow(){
        JFrame billsFrame = new JFrame("View Bills");
        billsFrame.setLayout(new BorderLayout());

        // Create a table to display bills
        JTable billsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(billsTable);
        billsTableModel = new DefaultTableModel();

        billsTable.setModel(billsTableModel);

        controller.showAllBillsInTable();

        // Add components to the client operations window
        billsFrame.add(scrollPane, BorderLayout.CENTER);

        billsFrame.setSize(600, 400);
        billsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billsFrame.setVisible(true);
    }

    /**
     * Displays the window for client operations.
     */
    private void showClientOperationsWindow() {
        JFrame clientFrame = new JFrame("Client Operations");
        clientFrame.setLayout(new BorderLayout());

        // Create a panel for client operations
        JPanel operationPanel = new JPanel();
        JButton addButton = new JButton("Add Client");
        JButton editButton = new JButton("Edit Client");
        JButton deleteButton = new JButton("Delete Client");
        operationPanel.add(addButton);
        operationPanel.add(editButton);
        operationPanel.add(deleteButton);

        // Create a table to display clients
        JTable clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        clientTableModel = new DefaultTableModel();


        clientTable.setModel(clientTableModel);

        controller.showAllClientsInTable();
        // Add action listeners for buttons
        addButton.addActionListener(e -> showAddClientFrame());

        editButton.addActionListener(e -> showUpdateClientFrame());

        deleteButton.addActionListener(e -> showDeleteClientFrame());

        // Add components to the client operations window
        clientFrame.add(operationPanel, BorderLayout.NORTH);
        clientFrame.add(scrollPane, BorderLayout.CENTER);

        clientFrame.setSize(600, 400);
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setVisible(true);
    }

    /**
     * Displays the window for product operations.
     */
    private void showProductOperationsWindow() {
        JFrame productFrame = new JFrame("Product Operations");
        productFrame.setLayout(new BorderLayout());

        // Create a panel for product operations
        JPanel operationPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        JButton editButton = new JButton("Edit Product");
        JButton deleteButton = new JButton("Delete Product");
        operationPanel.add(addButton);
        operationPanel.add(editButton);
        operationPanel.add(deleteButton);

        // Create a table to display products
        JTable productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        productTableModel = new DefaultTableModel();

        // Add more columns as needed
        productTable.setModel(productTableModel);

        controller.showAllProductsInTable();

        // Add action listeners for buttons
        addButton.addActionListener(e -> showAddProductFrame());

        editButton.addActionListener(e -> showUpdateProductFrame());

        deleteButton.addActionListener(e -> showDeleteProductFrame());

        // Add components to the product operations window
        productFrame.add(operationPanel, BorderLayout.NORTH);
        productFrame.add(scrollPane, BorderLayout.CENTER);

        productFrame.setSize(600, 400);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setVisible(true);
    }

    /**
     * Displays the window for order operations.
     */
    private void showOrderOperationsWindow() {
        JFrame productFrame = new JFrame("Order Operations");
        productFrame.setLayout(new BorderLayout());

        // Create a panel for product operations
        JPanel operationPanel = new JPanel();
        JButton createButton = new JButton("Create order");

        operationPanel.add(createButton);


        // Create a table to display products
        JTable orderTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderTableModel = new DefaultTableModel();
        orderTable.setModel(orderTableModel);

        controller.showAllOrdersInTable();

        // Add action listeners for buttons
        createButton.addActionListener(e -> showCreateOrderWindow());

        // Add components to the product operations window
        productFrame.add(operationPanel, BorderLayout.NORTH);
        productFrame.add(scrollPane, BorderLayout.CENTER);

        productFrame.setSize(600, 400);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setVisible(true);
    }

    /**
     * Displays the window for creating a new order.
     */
    private void showCreateOrderWindow() {
        JFrame orderFrame = new JFrame("Create Product Order");
        orderFrame.setLayout(new GridLayout(4, 2));

        JLabel productLabel = new JLabel("Select Product:");
        ArrayList<String[]> products = controller.getProductsNameAndId();
        String[] productsNames =  products.getFirst();
        String[] productsIds =  products.get(1);
        JComboBox<String> productComboBox = new JComboBox<>(productsNames);

        JLabel clientLabel = new JLabel("Select Client:");
        ArrayList<String[]> clients = controller.getClientsNameAndId();
        String[] clientsNames = clients.getFirst();
        String[] clientsIds = clients.get(1);
        JComboBox<String> clientComboBox = new JComboBox<>(clientsNames);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JButton createOrderButton = new JButton("Create Order");
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected product, client, and quantity
                String selectedProduct = (String) productComboBox.getSelectedItem();
                int i=0;
                for(String s : productsNames){
                    if(s.equals(selectedProduct)){
                        break;
                    }
                    i++;
                }
                int selectedProductId = Integer.parseInt(productsIds[i]);

                String selectedClient = (String) clientComboBox.getSelectedItem();
                i=0;
                for(String s: clientsNames){
                    if(s.equals(selectedClient)){
                        break;
                    }
                    i++;
                }
                int selectedClientId = Integer.parseInt(clientsIds[i]);

                int quantity = Integer.parseInt(quantityField.getText()); // Assuming quantity is always valid

                Orders order = new Orders(selectedClientId, selectedProductId, quantity);
                if(!controller.addOrder(order)) {
                    JOptionPane.showMessageDialog(orderFrame, "Not enough stock of the selected product.", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    // You can handle the order creation here, e.g., pass the data to the controller
//                    System.out.println("Creating order for Product: " + selectedProduct +
//                            "with id" + selectedProductId +
//                            ", Client: " + selectedClientId +
//                            ", Quantity: " + quantity);

                    // Close the order frame
                    orderFrame.dispose();
                }
            }
        });

        orderFrame.add(productLabel);
        orderFrame.add(productComboBox);
        orderFrame.add(clientLabel);
        orderFrame.add(clientComboBox);
        orderFrame.add(quantityLabel);
        orderFrame.add(quantityField);
        orderFrame.add(new JLabel());
        orderFrame.add(createOrderButton);

        orderFrame.setSize(400, 200);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setVisible(true);
    }

    /**
     * Displays the window for adding a new client.
     */
    private void showAddClientFrame() {
        JFrame addClientFrame = new JFrame("Add Client");
        addClientFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton addButton = new JButton("Add Client");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText()); // Assuming age is an integer
                String email = emailField.getText();

                // Create Client object with entered values
                Client client = new Client(name, email, age);

                // Call the method in the controller to add the client
                controller.addClient(client);

                // Close the add client frame
                addClientFrame.dispose();
            }
        });

        addClientFrame.add(nameLabel);
        addClientFrame.add(nameField);
        addClientFrame.add(emailLabel);
        addClientFrame.add(emailField);
        addClientFrame.add(ageLabel);
        addClientFrame.add(ageField);
        addClientFrame.add(new JLabel());
        addClientFrame.add(addButton);

        addClientFrame.setSize(300, 200);
        addClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addClientFrame.setVisible(true);
    }

    /**
     * Displays the window for updating an existing client.
     */
    private void showUpdateClientFrame() {
        JFrame updateClientFrame = new JFrame("Update Client");
        updateClientFrame.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("Client ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton updateButton = new JButton("Update Client");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                int id = Integer.parseInt(idField.getText());
                String newName = nameField.getText();
                int newAge = Integer.parseInt(ageField.getText()); // Assuming age is an integer
                String newEmail = emailField.getText();

                // Create Client object with updated values
                Client updatedClient = new Client(id, newName, newEmail, newAge);

                // Call the method in the controller to update the client
                controller.updateClient(updatedClient);

                // Close the update client frame
                updateClientFrame.dispose();
            }
        });

        updateClientFrame.add(idLabel);
        updateClientFrame.add(idField);
        updateClientFrame.add(nameLabel);
        updateClientFrame.add(nameField);
        updateClientFrame.add(emailLabel);
        updateClientFrame.add(emailField);
        updateClientFrame.add(ageLabel);
        updateClientFrame.add(ageField);
        updateClientFrame.add(new JLabel());
        updateClientFrame.add(updateButton);

        updateClientFrame.setSize(300, 200);
        updateClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateClientFrame.setVisible(true);
    }

    /**
     * Displays the window for deleting an existing client.
     */
    private void showDeleteClientFrame() {
        JFrame deleteClientFrame = new JFrame("Delete Client");
        deleteClientFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Client ID:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Delete Client");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the ID entered by the user
                int clientId = Integer.parseInt(idField.getText());

                // Call the method in the controller to delete the client
                controller.deleteClient(clientId);

                // Close the delete client frame
                deleteClientFrame.dispose();
            }
        });

        deleteClientFrame.add(idLabel);
        deleteClientFrame.add(idField);
        deleteClientFrame.add(new JLabel());
        deleteClientFrame.add(deleteButton);

        deleteClientFrame.setSize(300, 100);
        deleteClientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteClientFrame.setVisible(true);
    }

    /**
     * Displays the window for adding a new product.
     */
    private void showAddProductFrame() {
        JFrame addProductFrame = new JFrame("Add Product");
        addProductFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel stockLabel = new JLabel("Stock:");
        JTextField stockField = new JTextField();

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());

                // Create Product object with entered values
                Product product = new Product(name, price, stock);

                // Call the method in the controller to add the product
                controller.addProduct(product);

                // Close the add product frame
                addProductFrame.dispose();
            }
        });

        addProductFrame.add(nameLabel);
        addProductFrame.add(nameField);
        addProductFrame.add(priceLabel);
        addProductFrame.add(priceField);
        addProductFrame.add(stockLabel);
        addProductFrame.add(stockField);
        addProductFrame.add(new JLabel());
        addProductFrame.add(addButton);

        addProductFrame.setSize(300, 200);
        addProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addProductFrame.setVisible(true);
    }

    /**
     * Displays the window for updating an existing product.
     */
    private void showUpdateProductFrame() {
        JFrame updateProductFrame = new JFrame("Update Product");
        updateProductFrame.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("Product ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel stockLabel = new JLabel("Stock:");
        JTextField stockField = new JTextField();

        JButton updateButton = new JButton("Update Product");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                int id = Integer.parseInt(idField.getText());
                String newName = nameField.getText();
                double newPrice = Double.parseDouble(priceField.getText());
                int newStock = Integer.parseInt(stockField.getText());

                // Create Product object with updated values
                Product updatedProduct = new Product(id, newName, newPrice, newStock);

                // Call the method in the controller to update the product
                controller.updateProduct(updatedProduct);

                // Close the update product frame
                updateProductFrame.dispose();
            }
        });

        updateProductFrame.add(idLabel);
        updateProductFrame.add(idField);
        updateProductFrame.add(nameLabel);
        updateProductFrame.add(nameField);
        updateProductFrame.add(priceLabel);
        updateProductFrame.add(priceField);
        updateProductFrame.add(stockLabel);
        updateProductFrame.add(stockField);
        updateProductFrame.add(new JLabel());
        updateProductFrame.add(updateButton);

        updateProductFrame.setSize(300, 200);
        updateProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateProductFrame.setVisible(true);
    }

    /**
     * Displays the window for deleting an existing product.
     */
    private void showDeleteProductFrame() {
        JFrame deleteProductFrame = new JFrame("Delete Product");
        deleteProductFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Product ID:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the ID entered by the user
                int productId = Integer.parseInt(idField.getText());

                // Call the method in the controller to delete the product
                controller.deleteProduct(productId);

                // Close the delete product frame
                deleteProductFrame.dispose();
            }
        });

        deleteProductFrame.add(idLabel);
        deleteProductFrame.add(idField);
        deleteProductFrame.add(new JLabel());
        deleteProductFrame.add(deleteButton);

        deleteProductFrame.setSize(300, 100);
        deleteProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteProductFrame.setVisible(true);
    }


    public DefaultTableModel getClientTableModel() {
        return clientTableModel;
    }

    public DefaultTableModel getProductTableModel() {
        return productTableModel;
    }

    public DefaultTableModel getOrderTableModel() {
        return orderTableModel;
    }

    public DefaultTableModel getBillsTableModel() {
        return billsTableModel;
    }
}
