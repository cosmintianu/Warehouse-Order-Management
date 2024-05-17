package business_logic;

import data_access.ClientDAO;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Provides business logic operations related to clients.
 */
public class ClientBLL {
    private final ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL instance.
     */
    public ClientBLL() {
        this.clientDAO = new ClientDAO();
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return A list of Client objects representing the clients.
     */
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    /**
     * Inserts a new client into the database.
     *
     * @param client The Client object to be inserted.
     */
    public void insert(Client client) {
        clientDAO.insert(client);
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The Client object to be updated.
     */
    public void update(Client client) {
        clientDAO.update(client);
    }

    /**
     * Deletes a client from the database based on its ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void delete(int id) {
        clientDAO.delete(id);
    }
}
