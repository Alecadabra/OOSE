package Controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import Model.Items.Item;
import Model.Items.Enchantments.Enchantable;

/**
 * Handles enchantments using reflective constructor objects.
 */
public class EnchantmentHandler
{
    HashMap<String, Constructor<?>> constructors;

    /**
     * Constructor.
     * @param enchantConstructors Array of Constructor<?>'s for enchantments
     */
    public EnchantmentHandler(Constructor<?>[] enchantConstructors)
    {
        this.constructors = new HashMap<>();
        for(Constructor<?> curr : enchantConstructors)
        {
            constructors.put(curr.getDeclaringClass().getName(), curr);
        }
    }

    /**
     * Get an array of loaded display instances of enchantments.
     * @return Item array of display enchantments
     * @throws EnchantmentHandlerException If an error occured
     */
    public Item[] load() throws EnchantmentHandlerException
    {
        // Needed to supress warning from Constructor.newInstance from passing
        // it null, as the enchantments created here have a 'next' of null
        Enchantable nullReference = null;
        Item[] items = new Item[this.constructors.size()];
        int i = 0;

        try
        {
            // Add a new instance of each stored enchantment into the list
            for(Constructor<?> constructor : constructors.values())
            {
                items[i] = (Item)constructor.newInstance(nullReference);
                i++;
            }
        }
        catch(ReflectiveOperationException e)
        {
            throw new EnchantmentHandlerException(String.format(
                "Could not construct enchantment; %s", e.getMessage()), e);
        }

        return items;
    }
    
    /**
     * Apply an enchantment to a given enchantable item. Works like a factory
     * for enchantments, and applies them to the item using the decorator
     * pattern.
     * @param enchant String of the class name of the enchantment, including
     * the package that contains it
     * @param item Enchantable item
     * @return The new enchantable item
     * @throws EnchantmentHandlerException If an error occured
     */
    public Enchantable enchant(String enchant, Enchantable item)
        throws EnchantmentHandlerException
    {
        Enchantable newItem;
        
        try
        {
            newItem = (Enchantable)constructors.get(enchant)
                .newInstance(new Object[] {item});
        }
        catch(ReflectiveOperationException | NullPointerException e)
        {
            throw new EnchantmentHandlerException(String.format(
                "Could not enchant %s with %s; %s",
                item.toString(), enchant, e.getMessage()), e);
        }

        return newItem;
    }

}
