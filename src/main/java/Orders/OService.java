package Orders;


import exceptions.OrderGeenCancelled;
import exceptions.OrderGeenPlace;
import exceptions.OrderNotFound;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class OService
{
    public ArrayList<Order> database = new ArrayList<Order>();

    public List<Order> getOrders()
    {
        return Database.getDataBase().getOrders();
    }

    public List<Order> getOrderByCustomerId(long id) throws OrderNotFound
    {
        return Database.getDataBase().getOrderByCustomerId(id);
    }

    public Order postOrder(ORequest orderRequest) throws OrderGeenPlace
    {
        return Database.getDataBase().addOrder(orderRequest);
    }

    public Order cancelOrder(long orderId) throws OrderGeenCancelled
    {
        return Database.getDataBase().cancelOrder(orderId);
    }

    public List<Object> getDeliveryTime(long orderId) throws OrderNotFound
    {
        Order order = Database.getDataBase().getOrderByOrderId(orderId);
        ETA delivery_time = new ETA(order.getOrderedAt().plusMinutes(25));
        return List.of(order, delivery_time);
    }
}

