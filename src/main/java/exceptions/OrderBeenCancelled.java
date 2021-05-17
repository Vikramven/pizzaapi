package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Cancelled")
public class OrderBeenCancelled extends Exception
{
    private static final long serialVersionUID = 1L;

    public OrderBeenCancelled(String errorMessage)
    {
        super(errorMessage);
    }
}

