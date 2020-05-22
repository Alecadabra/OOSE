import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Alec
 */
public class AddressBook
{
    HashMap<String, ArrayList<String>> emails;
    HashMap<String, String> names;

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
        ArrayList<String> emailList = new ArrayList<>();
            // List of emails for this name
        
        for(int i = 1; i < elem.length; i++)
        {
            emailList.add(elem[i]);
                // Populate email list

            names.put(elem[i], elem[0]);
                // Populate names map for each email
        }

        emails.put(elem[0], emailList);
            // Populate emails map for this name
    }
    
    /**
     * Finds the email address(es) for the given name
     * @param name The name to look up
     * @return ArrayList of string(s) of email(s)
     * @throws NoSuchElementException If no entry is found for the name
     */
    public ArrayList<String> findEmail(String name) 
        throws NoSuchElementException
    {
        ArrayList<String> emailList;
        
        emailList = emails.get(name);

        if(emailList == null)
        {
            throw new NoSuchElementException("No name \"" + name +
                "\" found in address book");
        }

        return emailList;
    }

    /**
     * Finds the name of the person with the given email address
     * @param email The email to search for
     * @return String of name found
     * @throws NoSuchElementException If no entry is found for the name
     */
    public String findName(String email) throws NoSuchElementException
    {
        String name;

        name = names.get(email);

        if(name == null)
        {
            throw new NoSuchElementException("No email \"" + email +
                "\" found in address book");
        }

        return name;
    }
}
