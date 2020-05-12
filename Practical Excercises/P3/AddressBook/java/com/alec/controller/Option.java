package controller;

import model.AddressBook;

public abstract class Option
{
    protected AddressBook addressBook;

    public abstract String doOption(String s);

    public abstract boolean requiresText();
}