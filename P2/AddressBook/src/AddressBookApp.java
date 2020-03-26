import java.io.*;
import java.util.*;
import java.util.Scanner;

//import javax.swing.text.html.Option;

/**
 * A simple address book application.
 * @author Dave and Alec
 */
public class AddressBookApp 
{
    private static ArrayList<Option> options;
        // List of searchbyemail (idx 0) and searchbyname (idx 1) options

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
            // Used to obtain user input
        AddressBook addressBook;
            // Address book of name/email(s) read from file
        String fileName;
            // File name to read address book data from

        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            addressBook = readAddressBook(fileName);
            options = populateOptions(addressBook);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + 
                e.getMessage());
        }

        input.close();
    }

    /**
     * Populate options
     * 
     * @return ArrayList<Option> populated by searchbyemail ([0]) and searchbyname ([1])
     */
    private static ArrayList<Option> populateOptions(AddressBook addressBook)
    {
        options = new ArrayList<>();

        options.add(new SearchByEmail(addressBook));
        options.add(new SearchByName(addressBook));

        return options;
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name string of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName)
        throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while(line != null)
        {
            addressBook.insert(line.split(":"));
            
            line = reader.readLine();
        }
        reader.close();
        
        return addressBook;
    }
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        String search, result;
        Scanner input = new Scanner(System.in);

        while(!done)
        {
            System.out.println(
                "(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:
                        // Search for emails by name
                        System.out.print("Enter name: ");
                        search = input.nextLine();
                        
                        try
                        {
                            result = options.get(0).doOption(search);
                            
                            System.out.println("Result found:\n" + result);
                        }
                        catch(NoSuchElementException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        
                        break;
                        
                    case 2:
                        // Search for names by email
                        System.out.print("Enter email address: ");
                        search = input.nextLine();
                        
                        try
                        {
                            result = options.get(1).doOption(search);
                            
                            System.out.println("Result found:\n" + result);
                        }
                        catch(NoSuchElementException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        
                        break;
                        
                    case 3:
                        // Exit
                        done = true;
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }

        input.close();
    }
}
