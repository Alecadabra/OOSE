package Model.Items.Enchantments;

import Model.Items.Item;
import Model.Items.ItemException;

/**
 * Defines an Item that can be enchanted, or an enchantment. Uses the decorator
 * pattern.
 */
public abstract class Enchantable extends Item
{
    /**
     * Constructor.
     * @param name Name of the item
     * @param cost Item's cost in gold when bought
     * @param minEffect Minimum value that this item can return on getEffect()
     * @param maxEffect Maximum value that this item can return on getEffect()
     */
    public Enchantable(String name, int cost, int minEffect, int maxEffect)
        throws ItemException
    {
        super(name, cost, minEffect, maxEffect);
    }

    /**
     * Determines if an item can be enchanted to extends it's functionality
     * when providing defence and/or dealing damage.
     * @return True if enchantable, false otherwise
     */
    public boolean isEnchantable()
    {
        return true;
    }
}
