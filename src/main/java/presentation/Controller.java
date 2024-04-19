package presentation;

import data_access.ClientDAO;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ClientDAO clientDAO;
    private View view;

    public Controller(View view) {
        this.view = view;
        this.clientDAO = new ClientDAO(); // Instantiate ClientDAO
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
}
