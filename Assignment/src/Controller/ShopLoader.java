package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Model.Items.Item;

/**
 * Uses the template method pattern to load Items for a ShopStorage object.
 * Extending classes must implement the standard Iterator methods to return
 * a new Item object on each iteration. Extending classes should throw
 * NoSuchElementException for any type of error with a detailed message, as
 * this is declared as thrown by iterator methods.
 */
public abstract class ShopLoader implements Iterator<Item>, Iterable<Item>
{
    /**
     * Loads an arraylist of items for use in a ShopStorage object. Uses the
     * template method pattern to iterate over itself, adding items to the
     * list.
     * @param list ArrayList of Items to fill
     */
    public void load(ArrayList<Item> list) throws ShopLoaderException
    {
        try
        {
            for(Item item : this)
            {
                list.add(item);
            }
        }
        catch(NoSuchElementException e)
        {
            throw new ShopLoaderException("Could not load shop item data; " +
                e.getMessage(), e);
        }
    }

    @Override
    public Iterator<Item> iterator()
    {
        return this;
    }
}