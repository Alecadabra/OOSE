package Model.Items;

/**
 * Represents a damage potion that can be used to deal damage to a character.
 */
public class DamagePotion extends Potion
{    
    /**
     * Constructor.
     * @param name Name of the potion
     * @param cost Potion's cost in gold when bought
     * @param minDamage Minimum value that this item can return on getDamage()
     * @param maxDamage Maximum value that this item can return on getDamage()
     */
    public DamagePotion(String name, int cost, int minDamage, int maxDamage)
    {
        super(name, cost, minDamage, maxDamage);
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