package pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/pizza")
public class PizzaController
{
    private final PService pizzaService;

    @Autowired
    public PizzaController(PService pizzaService)
    {
        this.pizzaService = pizzaService;
    }

    @GetMapping()
    public List<Pizza> getPizza()
    {
        return pizzaService.getPizza();
    }

    @GetMapping(value = "/{id}")
    public List<Pizza> getPizza(@PathVariable long id)
    {
        try {
            return pizzaService.getPizzaById(id);
        } catch(PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza_ID_Not_Found", e);
        }
    }
}

