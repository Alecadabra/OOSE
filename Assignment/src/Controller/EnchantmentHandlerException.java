package Controller;

/**
 * An exception thrown by EnchantmentHandler
 */
public class EnchantmentHandlerException extends Exception
{
    private static final long serialVersionUID = 1L;

    public EnchantmentHandlerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EnchantmentHandlerException(String message)
    {
        super(message);
    }
}