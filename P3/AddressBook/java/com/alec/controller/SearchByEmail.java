package com.alec.controller;

import java.util.*;

import com.alec.model.AddressBook;

public class SearchByEmail extends Option
{
    /**
     * Constructor
     */
    protected SearchByEmail(AddressBook inAddressBook)
    {
        addressBook = inAddressBook;
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