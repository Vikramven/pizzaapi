package pizza;
import java.util.List;
import Database.Database;
import exceptions.PizzaGeenVind;
import org.springframework.stereotype.Service;



@Service
public class PService
{

    public List<Pizza> getPizza()
    {
        return Database.getDataBase().getPizzas();
    }

    public List<Pizza> getPizzaById(long id) throws PizzaGeenVind
    {
        return Database.getDataBase().getPizzaById(id);
    }
}

