package Orders;

import java.util.List;

import exceptions.OrderBeenCancelled;
import exceptions.OrderGeenPlace;
import exceptions.OrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizza.PRequest;


@RestController
@RequestMapping(path = "/api/order")
public class OrderController
{
    private final OService orderService;

    @Autowired
    public OrderController(OService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getOrders()
    {
        return orderService.getOrders();
    }

    @GetMapping(value = "/{customer_id}")
    public List<Order> getOrderById(@PathVariable long customer_id) throws OrderNotFound {
        return orderService.getOrderByCustomerId(customer_id);
    }

    @GetMapping(value = "/pr")
    public PRequest getPizzareq()
    {
        return new PRequest();
    }

    @GetMapping(value = "/deliverytime/{order_id}")
    public List<Object> getDeliveryTime(@PathVariable long order_id) throws OrderNotFound {
        return orderService.getDeliveryTime(order_id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order makeOrder(@RequestBody ORequest orderRequest) throws OrderGeenPlace {
        return orderService.postOrder(orderRequest);
    }

    @RequestMapping(value = "cancel/{order_id}")
    public Order cancelOrder(@PathVariable long order_id) throws OrderBeenCancelled {
        return orderService.cancelOrder(order_id);
    }
}
