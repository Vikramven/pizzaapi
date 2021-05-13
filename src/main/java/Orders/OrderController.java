package Orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
    public List<Order> getOrderById(@PathVariable long customer_id)
    {
        try {
            return orderService.getOrderByCustomerId(customer_id);
        } catch(OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order_Not_Found", e);
        }
    }

    @GetMapping(value = "/pr")
    public PRequest getPizzareq()
    {
        return new PRequest();
    }

    @GetMapping(value = "/deliverytime/{order_id}")
    public List<Object> getDeliveryTime(@PathVariable long order_id)
    {
        try {
            return orderService.getDeliveryTime(order_id);
        } catch(OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order_Not_Found", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order makeOrder(@RequestBody ORequest orderRequest)
    {
        try {
            return orderService.postOrder(orderRequest);
        } catch (OrderNotPlacedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order_Not_Posted", e);
        }
    }

    @RequestMapping(value = "cancel/{order_id}")
    public Order cancelOrder(@PathVariable long order_id)
    {
        try {
            return orderService.cancelOrder(order_id);
        } catch (OrderNotCancelledException e) {
            if(e.getMessage().equalsIgnoreCase("Unable to cancel an already cancelled order"))
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
            if(e.getMessage().equalsIgnoreCase("Unable to cancel an already delivered order"))
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
