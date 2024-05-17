package business_logic;

import data_access.BillDAO;
import model.Bill;

import java.util.List;

/**
 * Provides business logic operations related to bills.
 */
public class BillBLL {
    private final BillDAO billDAO;

    /**
     * Constructs a new BillBLL instance.
     */
    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    /**
     * Retrieves a list of all bills.
     *
     * @return A list of Bill objects representing the bills.
     */
    public List<Bill> findAll() {
        return billDAO.findAll();
    }
}
