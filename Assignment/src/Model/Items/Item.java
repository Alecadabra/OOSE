package Model.Items;

import static java.lang.Math.random;
import static java.lang.Math.round;

/** 
 * Represents an item that can be stored in a player character's inventory or
 * for sale in a shop.
 */
public abstract class Item
{
    protected String name;
    protected int cost;
    protected int minEffect;
    protected int maxEffect;

    /**
     * Constructor.
     * @param name Name of the item
     * @param cost Item's cost in gold when bought
     * @param minEffect Minimum value that this item can return on getEffect()
     * @param maxEffect Maximum value that this item can return on getEffect()
     */
    public Item(String name, int cost, int minEffect, int maxEffect)
        throws ItemException
    {
        if(verifyStringAttr(name) &&
            verifyCost(cost) &&
            verifyEffectRange(minEffect, maxEffect))
        {
            this.name = name;
            this.cost = cost;
            this.minEffect = minEffect;
            this.maxEffect = maxEffect;
        }
        else
        {
            throw new ItemException(String.format("Invalid values for item " +
                "(name = %s, cost = %d, effect = %d-%d)",
                name, cost, minEffect, maxEffect));
        }
    }

    /**
     * Get a copy of this object.
     */
    public abstract Item clone();

    /**
     * Get the name of the item.
     * @return Item name string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get a more verbose description of the item. By default this is
     * equivalent to getName(), but should be overriden if there is more info
     * to give on an item.
     * @return Description string
     */
    public String getDescription()
    {
        return name;
    }

    /**
     * Get the cost of this item when bought in the shop.
     * @return Cost integer
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Get the sell price for this item when sold to the shop. This is half the
     * cost of the item (rounded down).
     * @return Sell cost integer
     */
    public int getSell()
    {
        return cost / 2;
    }

    /**
     * Effect this item has when used. Gives an integer between minEffect and
     * maxEffect. This should be used when overriding getDamage, getDefence and
     * getHealing based on what the item does. Eg, a damage dealing item would
     * do {@code getEffect()} damage.
     * @return Randomly generated number between minEffect and maxEffect
     */
    protected int getEffect()
    {
        return (int)round(minEffect + random() * (maxEffect - minEffect));
    }

    /**
     * Damage done by this item. Zero by default, override this method if the
     * item does damage.
     * @return Damage amount
     */
    public int getDamage()
    {
        return 0;
    }

    /**
     * Defence provided by this item. Zero by default, override this method if
     * the item does provides defence.
     * @return Defence amount
     */
    public int getDefence()
    {
        return 0;
    }

    /**
     * Healing given. Zero by default, override this method if the item heals.
     * @return Healing amount
     */
    public int getHealing()
    {
        return 0;
    }

    /**
     * Determines if an item can be equipped as a damage dealing item by a
     * player character. False by default, override this method if the item
     * is equipabble.
     * @return True if equipabble, false otherwise
     */
    public boolean isEquipabble()
    {
        return false;
    }

    /**
     * Determines if an item can be worn as a defence increasing item by a
     * player character. False by default, override this method if the item
     * is wearable.
     * @return True if wearable, false otherwise
     */
    public boolean isWearable()
    {
        return false;
    }

    /**
     * Determines if an item can be used as a damage dealing and/or healing
     * item by a player character. False by default, override this method if
     * the item is usable.
     * @return True if usable, false otherwise
     */
    public boolean isUsable()
    {
        return false;
    }

    /**
     * Determines if an item can be enchanted to extends it's functionality
     * when providing defence and/or dealing damage. False by default, override
     * this method if the item is enchantable.
     * @return True if enchantable, false otherwise
     */
    public boolean isEnchantable()
    {
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean equal = false;

        if(o instanceof Item)
        {
            if(((Item)o).getName().equals(getName()))
            {
                equal = true;
            }
        }

        return equal;
    }

    @Override
    public String toString()
    {
        return getDescription();
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
    protected static boolean verifyCost(int cost)
    {
        return cost >= 0;
    }
}
