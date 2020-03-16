import java.util.*;

public class SearchByName implements Option
{
    private HashMap<String, ArrayList<String>> emails;

    /**
     * Default constructor
     */
    protected SearchByName(AddressBook addressBook)
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
     * 
     * @param name The name to look up
     * @return ArrayList of string(s) of email(s)
     * @throws NoSuchElementException If no entry is found for the name
     */
    @Override public String doOption(String s)
        throws NoSuchElementException
    {
        ArrayList<String> emailList;
        
        emailList = emails.get(s);

        if(emailList == null)
        {
            throw new NoSuchElementException("No name \"" + s +
                "\" found in address book");
        }

        return emailList.toString();
    }
}