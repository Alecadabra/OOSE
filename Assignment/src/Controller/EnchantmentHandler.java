package Controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

import Model.Items.Item;
import Model.Items.Enchantments.Enchantable;
import Model.Items.Enchantments.Enchantment;

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
     * Load display instances of enchantments into an list.
     * @param items List to populate
     * @throws EnchantmentHandlerException If an error occured
     */
    public void load(List<Item> items) throws EnchantmentHandlerException
    {
        // Needed to supress warning from Constructor.newInstance from passing
        // it null, as the enchantments created here have a 'next' of null
        Enchantable nullReference = null;

        try
        {
            // Add a new instance of each stored enchantment into the list
            for(String key : constructors.keySet())
            {
                items.add(
                    (Enchantment)constructors.get(key).newInstance(
                        nullReference));
            }
        }
        catch(ReflectiveOperationException e)
        {
            throw new EnchantmentHandlerException(String.format(
                "Could not construct enchantment; %s", e.getMessage()), e);
        }
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
