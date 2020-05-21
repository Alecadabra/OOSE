package View;

/**
 * An exception thrown by EnemyFactory
 */
public class UserInterfaceException extends Exception
{
    private static final long serialVersionUID = 1L;

    public UserInterfaceException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserInterfaceException(String message)
    {
        super(message);
    }
}