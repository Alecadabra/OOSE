package controller;

import java.util.*;

import model.AddressBook;

public class SearchByEmail extends Option
{
    /**
     * Constructor
     */
    public SearchByEmail(AddressBook inAddressBook)
    {
        super();
        super.addressBook = inAddressBook;
    }

    /**
     * Finds the email address(es) for the given name
     * 
     * @param s The email string to look up
     * @return Result string
     * @throws NoSuchElementException If no entry is found for the name
     */
    @Override public String doOption(String s) throws NoSuchElementException
    {
        return addressBook.findName(s);
    }

    @Override public boolean requiresText()
    {
        return true;
    }
}