import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Alec
 */
public class AddressBook
{
    HashMap<String, String> emails;
    HashMap<String, String> names;

    public AddressBook()
    {
        emails = new HashMap<>();
        names = new HashMap<>();
    }
}
