package business_logic;

import data_access.BillDAO;
import data_access.OrderDAO;
import data_access.ProductDAO;
import model.Bill;
import model.Orders;
import model.Product;

import java.util.List;

/**
 * Provides business logic operations related to orders.
 */
public class OrdersBLL {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private BillDAO billDAO;

    /**
     * Constructs a new OrdersBLL instance.
     */
    public OrdersBLL() {
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
        this.billDAO = new BillDAO();
    }

    /**
     * Adds a new order to the system.
     *
     * @param order The Orders object representing the order to be added.
     * @return true if the order was successfully added, false otherwise.
     */
    public boolean addOrder(Orders order){
        List<Product> products = productDAO.findAll();
        int i = 0;
        for(Product p : products){
            if(p.getId() == order.getProductId()){
                break;
            }
            i++;
        }

        if(products.get(i).getStock() < order.getQuantity()){
            return  false;
        }else {
            orderDAO.insert(order);
            products.get(i).setStock(products.get(i).getStock() - order.getQuantity());
            productDAO.update(products.get(i));
            List <Orders> orders= orderDAO.findAll();
            int neededId = -1;
            for(Orders o: orders) {
                if (o.getClientId() == order.getClientId()
                        && o.getProductId() == order.getProductId()
                        && o.getQuantity() == order.getQuantity()) {
                    neededId = o.getId();
                }
            }
            Bill bill = new Bill(neededId, order.getQuantity() * products.get(i).getPrice());
            billDAO.insert(bill);
            return true;
        }
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of Orders objects representing the orders.
     */
    public List<Orders> findAll(){
        return orderDAO.findAll();
    }
}
