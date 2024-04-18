package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JFrame mainFrame;
    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;

    public View() {
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
        clientTableModel.addColumn("Address");
        // Add more columns as needed
        clientTable.setModel(clientTableModel);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add client functionality
                // This could open a new window to enter client details
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement edit client functionality
                // This could open a dialog to select a client to edit
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete client functionality
                // This could delete the selected client from the table and database
            }
        });

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
}
