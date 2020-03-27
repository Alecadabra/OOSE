package p3.addressbook.controller;

public abstract class Option
{
    protected AddressBook addressBook;

    public abstract String doOption(String s);

    public abstract boolean requiresText();
}