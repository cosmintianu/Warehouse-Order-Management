package business_logic;

import data_access.ClientDAO;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientBLL {
    ClientDAO clientDAO;

    public ClientBLL() {
        this.clientDAO = new ClientDAO();
    }

    public List<Client> findAll() {
        // Fetch all clients from the database using ClientDAO
        return clientDAO.findAll();
    }

    public void insert(Client client) {
        clientDAO.insert(client);
    }

    public void update(Client client){
        clientDAO.update(client);
    }

    public void delete(int id){
        clientDAO.delete(id);
    }
}
