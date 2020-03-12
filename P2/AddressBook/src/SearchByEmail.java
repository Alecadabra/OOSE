import java.util.*;

public class SearchByEmail implements Option
{
    private HashMap<String, ArrayList<String>> emails;

    /**
     * Default constructor
     */
    protected SearchByEmail()
    {
        emails
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