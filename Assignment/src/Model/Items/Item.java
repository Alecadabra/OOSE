package Model.Items;

import static java.lang.Math.random;

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
    {
        this.name = name;
        this.cost = cost;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

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
        return (int)(minEffect + random() * (maxEffect - minEffect));
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

    @Override
    public boolean equals(Object o)
    {
        boolean equal = false;

        if(o instanceof Item)
        {
            if(((Item)o).getDescription().equals(getDescription()))
            {
                equal = true;
            }
        }

        return equal;
    }
}
