package data_access;

import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static connection.ConnectionFactory.getConnection;

/**
 * Provides data access functionality for bills.
 */
public class BillDAO extends AbstractDAO<Bill> {

    /**
     * Constructs a new BillDAO instance.
     */
    public BillDAO() {
        super();
    }

    /**
     * Retrieves a list of all bills from the database.
     *
     * @return A list of Bill objects representing the bills.
     */
    @Override
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT id, orderId, total FROM log";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderId");
                double total = rs.getDouble("total");
                Bill bill = new Bill(id, orderId, total);
                bills.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

}
