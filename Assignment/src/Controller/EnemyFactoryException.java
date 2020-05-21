package Controller;

/**
 * An exception thrown by EnemyFactory
 */
public class EnemyFactoryException extends Exception
{
    private static final long serialVersionUID = 1L;

    public EnemyFactoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EnemyFactoryException(String message)
    {
        super(message);
    }
}