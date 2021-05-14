package pizza;
import java.util.List;

import org.springframework.stereotype.Service;
import


@Service
public class PService
{

    public List<Pizza> getPizza()
    {
        return Database.getDataBase().getPizzas();
    }

    public List<Pizza> getPizzaById(long id) throws PizzaNotFoundException
    {
        return DataBase.getDataBase().getPizzaById(id);
    }
}

