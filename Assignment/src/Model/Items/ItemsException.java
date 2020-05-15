package Model.Items;

public class ItemsException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ItemsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ItemsException(String message)
    {
        super(message);
    }
}