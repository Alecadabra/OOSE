package Model.Items;

/**
 * Implementation of a DefaultPotion, represents a damage potion that can be
 * used to deal damage to a character. Damage potions deal damage in the range
 * given.
 */
public class DamagePotion extends DefaultPotion
{    
    /**
     * Constructor.
     * @param name Name of the potion
     * @param cost Potion's cost in gold when bought
     * @param minDamage Minimum value that this item can return on getDamage()
     * @param maxDamage Maximum value that this item can return on getDamage()
     */
    public DamagePotion(String name, int cost, int minDamage, int maxDamage)
        throws ItemException
    {
        super(name, cost, minDamage, maxDamage);
    }

    public Item clone()
    {
        Item clone;
        
        try
        {
            clone = new DamagePotion(name, cost, minEffect, maxEffect);
        }
        catch(ItemException e)
        {
            // Should never happen
            clone = null;
        }

        return clone;
    }

    /**
     * Gets the description string of a damage potion, consisting of it's name
     * and damage, eg: {@code Explosive Potion (20-20 damage)}.
     * @return Description string
     */
    @Override
    public String getDescription()
    {
        return String.format("%s (%d-%d damage)",
            name, minEffect, maxEffect);
    }

    /**
     * Gets the damage dealt by this potion, a randomly generated number
     * between minDamage and maxDamage.
     * @return Damage integer
     */
    @Override
    public int getDamage()
    {
        return getEffect();
    }
}