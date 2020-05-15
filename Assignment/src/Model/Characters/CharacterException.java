package Model.Characters;

/**
 * An Exception thrown by classes in Model.Characters
 */
public class CharacterException extends Exception
{
    private static final long serialVersionUID = 1L;

    public CharacterException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CharacterException(String message)
    {
        super(message);
    }
}