package Model.Items;

public class ItemException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ItemException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ItemException(String message)
    {
        super(message);
    }
}