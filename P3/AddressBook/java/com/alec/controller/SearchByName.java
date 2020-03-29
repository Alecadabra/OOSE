package com.alec.controller;

import java.util.NoSuchElementException;

import com.alec.model.AddressBook;

public class SearchByName extends Option
{
    /**
     * Constructor
     */
    protected SearchByName(AddressBook inAddressBook)
    {
        addressBook = inAddressBook;
    }

    /**
     * Finds the email address(es) for the given name
     * 
     * @param s The name string to look up
     * @return Result string
     * @throws NoSuchElementException If no entry is found for the name
     */
    @Override public String doOption(String s) throws NoSuchElementException
    {
        return addressBook.findEmail(s);
    }

    @Override public boolean requiresText()
    {
        return true;
    }
}