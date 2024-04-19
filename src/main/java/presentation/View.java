package presentation;

import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private Controller controller;
    private JFrame mainFrame;
    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;

    public View() {
        controller = new Controller(this);
        mainFrame = new JFrame("Inventory Management System");
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        // Add buttons for client operations and product operations
        JButton clientButton = new JButton("Client Operations");
        JButton productButton = new JButton("Product Operations");
        JButton orderButton = new JButton("Create Product Order");

        // Add action listeners for buttons
        clientButton.addActionListener(e -> showClientOperationsWindow());

        productButton.addActionListener(e -> showProductOperationsWindow());

        orderButton.addActionListener(e -> showCreateOrderWindow());

        mainFrame.add(clientButton);
        mainFrame.add(productButton);
        mainFrame.add(orderButton);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

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
        clientTableModel.addColumn("ID");
        clientTableModel.addColumn("Name");
        clientTableModel.addColumn("E-mail");
        clientTableModel.addColumn("Age");
        // Add more columns as needed
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
        productTableModel.addColumn("ID");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Price");
        productTableModel.addColumn("Stock");
        // Add more columns as needed
        productTable.setModel(productTableModel);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add product functionality
                // This could open a new window to enter product details
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement edit product functionality
                // This could open a dialog to select a product to edit
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete product functionality
                // This could delete the selected product from the table and database
            }
        });

        // Add components to the product operations window
        productFrame.add(operationPanel, BorderLayout.NORTH);
        productFrame.add(scrollPane, BorderLayout.CENTER);

        productFrame.setSize(600, 400);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setVisible(true);
    }

    private void showCreateOrderWindow() {
        JFrame orderFrame = new JFrame("Create Product Order");
        // Add components for creating product orders
        orderFrame.setSize(600, 400);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setVisible(true);
    }

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
                Client updatedClient = new Client(id,newName,newEmail,newAge);

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


    public DefaultTableModel getClientTableModel() {
        return clientTableModel;
    }
}
