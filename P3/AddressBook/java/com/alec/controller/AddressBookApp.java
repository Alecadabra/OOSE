package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//import controller.OptionHandler;
import model.AddressBook;
import view.UI;

//import javax.swing.text.html.Option;

/**
 * A simple address book application.
 * @author Dave and Alec
 */
public class AddressBookApp 
{
    public static void main(String[] args)
    {
        OptionHandler optHandler;
            // Handles search options
        AddressBook addressBook;
            // Address book of name/email(s) read from file
        String fileName;
            // File name to read address book data from

        fileName = UI.input("Enter address book filename: ");
        
        try
        {
            addressBook = readAddressBook(fileName);

            optHandler = new OptionHandler();
            optHandler.addOption(new SearchByName(addressBook));
            optHandler.addOption(new SearchByEmail(addressBook));

            UI.showMenu(optHandler);
        }
        catch(IOException e)
        {
            UI.printErr("Could not read from " + fileName + ": " + 
                e.getMessage());
        }
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
}
