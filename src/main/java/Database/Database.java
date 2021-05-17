package Database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

;
import Orders.Address;
import Orders.ORequest;
import Orders.Order;
import Orders.State;
import exceptions.OrderBeenCancelled;
import exceptions.OrderGeenPlace;
import exceptions.OrderNotFound;
import exceptions.PizzaGeenVind;
import pizza.PRequest;
import pizza.Pizza;

public class Database
{
    private final int NO_CANCEL_AFTER = 5; //minutes

    private static Database dataBase = new Database();

    public long highestCustomerId = 1L;
    public long highestOrderId = 1L;
    public List<Pizza> pizzas = new ArrayList<Pizza>();
    public List<Order> orders = new ArrayList<Order>();


    public static Database getDataBase()
    {
        if(dataBase == null)
            dataBase = new Database();
        return dataBase;
    }

    public List<Pizza> getPizzas()
    {
        return pizzas;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public List<Pizza> getPizzaById(long id) throws PizzaGeenVind
    {
        for(Pizza each: pizzas)
        {
            if(each.getId() == id)
                return List.of(each);
        }
        throw new PizzaGeenVind("Pizza Not Found");
    }

    public List<Order> getOrderByCustomerId(long id) throws OrderNotFound
    {
        for(Order each: orders)
        {
            if(each.getCustomerId() == id)
                return List.of(each);
        }
        throw new OrderNotFound("Customer Not Found");
    }

    public Order getOrderByOrderId(long id) throws OrderNotFound
    {
        for(Order each: orders)
        {
            if(each.getId() == id)
                return each;
        }
        throw new OrderNotFound("Customer Not Found");
    }

    public Order addOrder(ORequest orderRequest) throws OrderGeenPlace
    {
        try {
            List<Pizza> pizzas = convertToPizzas(orderRequest.getPizzas());
        } catch (PizzaGeenVind e) {
            throw new OrderGeenPlace("Unable to locate pizza");
        }

        Order order = new Order(highestOrderId++,
                orderRequest.getCustomer_id(),
                "IN_PROGRESS",
                LocalDateTime.now(),
                orderRequest.isTakeaway(),
                orderRequest.getPayment_type(),
                orderRequest.getDelivery_address(),
                pizzas);
        orders.add(order);
        return order;
    }

    public Order cancelOrder(long orderId) throws OrderBeenCancelled
    {
        Order order;
        try {
            order = getOrderByOrderId(orderId);
        } catch (OrderNotFound e) {
            throw new OrderBeenCancelled("Order not found");
        }
        if(ableToCancel(order)){
            if(order.getStatus()== State.CANCELLED)
                throw new OrderBeenCancelled("Unable to cancel an already cancelled order");
            if(order.getStatus()==State.DELIVERED)
                throw new OrderBeenCancelled("Unable to cancel an already delivered order");
        }
        order.setStatus(State.CANCELLED);
        return order;
    }

    private Pizza getPizzaByRequest(PRequest request) throws PizzaGeenVind
    {
        for(Pizza each: pizzas)
        {
            if(each.getId() == request.getId())
                return each;
        }
        throw new PizzaGeenVind("Pizza Not Found");
    }

    private List<Pizza> convertToPizzas(List<PRequest> list) throws PizzaGeenVind
    {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        for(PRequest each: list)
        {
            pizzas.add(getPizzaByRequest(each));
        }
        return pizzas;
    }

    private boolean ableToCancel(Order order)
    {
        LocalDateTime lastMomentToChange = order.getOrderedAt().plusMinutes(NO_CANCEL_AFTER);
        if(LocalDateTime.now().isBefore(lastMomentToChange))
            return true;
        return false;
    }


    // -----------------------------------------------------------------------------------
    /* Initialise a mock database for testing  */
    private Database()
    {
        String[] magTop = {"Mozzarella", "Tomatoes", "Bazil"};
        pizzas.add(new Pizza(0L,
                "margarita",
                true,
                12.50,
                magTop));

        String[] meatTop = {"Meat", "More Meat", "Mystery Meat"};
        pizzas.add(new Pizza(1L,
                "meatlovers",
                false,
                15.75,
                meatTop));
        Address address = new Address("Tongerseweg 57B25",
                "Maastricht",
                "6213GA",
                "Nederland");

        orders.add(new Order(0,
                1233456789,
                "ACCEPTED",
                LocalDateTime.now(),
                true,
                "buttons",
                address,
                List.of(pizzas.get(0), pizzas.get(0))));
    }
}