package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Model.Items.Item;

/**
 * Uses the template method pattern to load shops for a ShopStorage object.
 * Extending classes must implement the standard Iterator methods to return
 * a new Item object on each iteration, with the Item fields validated with the
 * protected validation methods provided. Extending classes should throw
 * NoSuchElementException for any type of error with a detailed message.
 */
public abstract class ShopLoader implements Iterator<Item>, Iterable<Item>
{
    /**
     * Loads an arraylist of items for use in a ShopStorage object. Uses the
     * template method pattern to iterate over itself, adding items to the
     * list.
     * @param list Empty ArrayList of Items to fill
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

    /**
     * Verify legality of a string attribute of an Item, for example a name.
     * String attributes are legal if they are non-empty.
     * @param name String to check
     * @return True if legal and false otherwise
     */
    protected boolean verifyStringAttr(String name)
    {
        return !name.equals("");
    }

    /**
     * Verify legality of a min/max effect range of an Item. Effect ranges are
     * legal if they are greater than or equal to zero, and the max is atleast
     * the min.
     * @param min Minimum effect value
     * @param max Maximum effect value
     * @return True if legal and false otherwise
     */
    protected boolean verifyEffectRange(int min, int max)
    {
        return min >= 0 && max >= 0 && min <= max;
    }

    /**
     * Verify legality of a cost attribue of an Item. Cost attributes are legal
     * if they are greater than or equal to zero.
     * @param cost The cost value
     * @return True if legal and false otherwise
     */
    protected boolean verifyCost(int cost)
    {
        return cost >= 0;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return this;
    }
}