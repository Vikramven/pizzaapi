package exceptions;


        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class OrderGeenPlace extends Exception
{
    private static final long serialVersionUID = 1L;

    public OrderGeenPlace(String errorMessage)
    {
        super(errorMessage);
    }
}
