import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Alec
 */
public class AddressBook
{
    HashMap<String, String> emails;
        // Map of emails, keyed by name
    HashMap<String, String> names;
        // Map of names, keyed by email

    /**
     * Default constructor
     */
    public AddressBook()
    {
        emails = new HashMap<>();
        names = new HashMap<>();
    }

    /**
     * Insert a name and email(s) into the address book
     * 
     * @param elem Array of address book elements, [0] being the name and
     * subsequent entries being emails
     */
    public void insert(String[] elem)
    {
        StringBuilder emailStr = new StringBuilder();
            // String of emails for this name
        
        for(int i = 1; i < elem.length; i++)
        {
            if(emailStr.toString().equals(""))
            {
                // First string being added
                emailStr.append(elem[i]);
            }
            else
            {
                // Not the first string for this name
                emailStr.append("\n" + elem[i]);
            }

            names.put(elem[i], elem[0]);
                // Populate names map for each email
        }

        emails.put(elem[0], emailStr.toString());
            // Populate emails map for this name
    }
    
    /**
     * Finds the email address(es) for the given name
     * @param String The name to look up
     * @return String of of email(s) found (Seperated by newlines)
     * @throws NoSuchElementException If no entry is found for the name
     */
    public String findEmail(String search) throws NoSuchElementException
    {
        String result = emails.get(search);

        if(result.toString().equals(""))
        {
            throw new NoSuchElementException("No name \"" + search +
                "\" found in address book");
        }

        return result;
    }

    /**
     * Finds the name of the person with the given email address
     * @param String The email to search for
     * @return String of name found
     * @throws NoSuchElementException If no entry is found for the name
     */
    public String findName(String search) throws NoSuchElementException
    {
        String result = names.get(search);

        if(result == null)
        {
            throw new NoSuchElementException("No email \"" + search +
                "\" found in address book");
        }

        return result;
    }
}
