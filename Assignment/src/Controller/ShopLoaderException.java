package Controller;

/**
 * An exception thrown by ShopLoader implementations
 */
public class ShopLoaderException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ShopLoaderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ShopLoaderException(String message)
    {
        super(message);
    }
}