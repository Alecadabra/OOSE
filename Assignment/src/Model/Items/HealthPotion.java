package Model.Items;

public class HealthPotion extends Potion
{
    /**
     * Constructor.
     * @param name Name of the potion
     * @param cost Potion's cost in gold when bought
     * @param minDefence Minimum value that this item can return on getHealing()
     * @param maxDefence Maximum value that this item can return on getHealing()
     */
    public HealthPotion(String name, int cost, int minHealing, int maxHealing)
        throws ItemException
    {
        super(name, cost, minHealing, maxHealing);
    }

    /**
     * Gets the description string of a health potion, consisting of it's name
     * and healing, eg: {@code Potion of Healing (5-10 healing)}.
     * @return Description string
     */
    @Override
    public String getDescription()
    {
        return String.format("%s (%d-%d healing)",
            name, minEffect, maxEffect);
    }

    /**
     * Gets the healing provided by this potion, a randomly generated number
     * between minEffect and maxEffect.
     * @return Healing integer
     */
    @Override
    public int getHealing()
    {
        return getEffect();
    }
}