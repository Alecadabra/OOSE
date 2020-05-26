package Controller;

import java.util.Iterator;
import java.util.LinkedList;
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
     * Gets an array of Items loaded from the concrete shoploader. Uses the
     * template method pattern to iterate over itself, adding items to the
     * list.
     * @return Array of loaded Items
     */
    public Item[] load() throws ShopLoaderException
    {
        LinkedList<Item> list = new LinkedList<>();
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

        return list.toArray(new Item[list.size()]);
    }

    @Override
    public Iterator<Item> iterator()
    {
        // Bit of a cheat
        return this;
    }
}