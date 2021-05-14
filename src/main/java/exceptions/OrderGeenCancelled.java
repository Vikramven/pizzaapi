package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Cancelled")
public class OrderGeenCancelled extends Exception
{
    private static final long serialVersionUID = 1L;

    public OrderGeenCancelled(String errorMessage)
    {
        super(errorMessage);
    }
}

