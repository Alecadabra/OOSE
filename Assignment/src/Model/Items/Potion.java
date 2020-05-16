package Model.Items;

/**
 * Represents a potion that can be used on a character to heal and/or damage.
 */
public abstract class Potion extends Item
{
    /**
     * Constructor.
     * @param name Name of the potion
     * @param cost Potion's cost in gold when bought
     * @param minEffect Minimum value that this item can return on getDamage()
     * or getHealing()
     * @param maxEffect Maximum value that this item can return on getDamage()
     * or getHealing()
     */
    public Potion(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }

    /**
     * Determines if an item can be used as a damage dealing and/or healing
     * item by a player character.
     * @return True if usable, false otherwise
     */
    @Override
    public boolean isUsable()
    {
        return true;
    }
}
