package Model.Items;

/**
 * Default implementation of Armour (Defined as a wearable Item that should
 * override getDefence in some way). Default Armour's provide defence in the
 * defence range given, and have a vanity string attribute for material.
 */
public class DefaultArmour extends Item
{
    String material;
    
    /**
     * Constructor.
     * @param name Name of the armour
     * @param cost Armour's cost in gold when bought
     * @param minDefence Minimum value that this item can return on getDefence()
     * @param maxDefence Maximum value that this item can return on getDefence()
     * @param material Material armour is made out of (Has no effect)
     */
    public DefaultArmour(String name, int cost, int minDefence, int maxDefence,
        String material) throws ItemException
    {
        super(name, cost, minDefence, maxDefence);
        if(verifyStringAttr(material))
        {
            this.material = material;
        }
        else
        {
            throw new ItemException(String.format(
                "Invalid value for armour (material = %s)",
                material));
        }
        
    }

    public Item clone()
    {
        Item clone;

        try
        {
            clone = new DefaultArmour(
                name, cost, minEffect, maxEffect, material);
        }
        catch(ItemException e)
        {
            // Should never happen
            clone = null;
        }

        return clone;
    }

    /**
     * Gets the description string of an armour, consisting of it's name and
     * defence, eg: {@code Chain Mail (10-18 defence)}.
     * @return Description string
     */
    @Override
    public String getDescription()
    {
        return String.format("%s (%d-%d defence)", name, minEffect, maxEffect);
    }

    /**
     * Gets the defence provided by this armour, a randomly generated number
     * between minDefence and maxDefence.
     * @return Defence integer
     */
    @Override
    public int getDefence()
    {
        return getEffect();
    }

    /**
     * Determines if an item can be worn as a defence increasing item by a
     * player character.
     * @return True if wearable, false otherwise
     */
    @Override
    public boolean isWearable()
    {
        return true;
    }
}