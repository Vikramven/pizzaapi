package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pizza Not Found")
public class PizzaGeenVind extends Exception
{
    private static final long serialVersionUID = 1L;

    public PizzaGeenVind(String errorMessage)
    {
        super(errorMessage);
    }
}

