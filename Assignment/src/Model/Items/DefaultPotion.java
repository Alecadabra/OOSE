package Model.Items;

/**
 * Default abstract implementation of a potion (Defined as a usable item that
 * should override gethealing and/or getDamage).
 */
public abstract class DefaultPotion extends Item
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
    public DefaultPotion(String name, int cost, int minEffect, int maxEffect)
        throws ItemException
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
