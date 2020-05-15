package Model.Items;

/**
 * Represents a potion that can be used on a character.
 */
public abstract class Potion extends Item
{
    /**
     * Constructor.
     * @param name Name of the potion
     * @param cost Potion's cost in gold when bought
     * @param minEffect Minimum value that this item can return on getEffect()
     * @param maxEffect Maximum value that this item can return on getEffect()
     */
    public Potion(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }
}
