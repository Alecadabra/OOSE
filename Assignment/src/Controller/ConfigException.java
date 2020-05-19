package Controller;

/**
 * An exception thrown by ConfigHandler
 */
public class ConfigException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ConfigException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ConfigException(String message)
    {
        super(message);
    }
}