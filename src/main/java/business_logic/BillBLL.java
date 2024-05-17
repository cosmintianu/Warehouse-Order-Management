package business_logic;

import data_access.BillDAO;
import model.Bill;

import java.util.List;

public class BillBLL {
    BillDAO billDAO;

    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    public List<Bill> findAll() {

        return billDAO.findAll();
    }
}
