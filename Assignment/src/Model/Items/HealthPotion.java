package Model.Items;

public class HealthPotion extends Potion
{
    public HealthPotion(String name, int cost, int minHealing, int maxHealing)
    {
        super(name, cost, minHealing, maxHealing);
    }

    @Override
    public int getHealing()
    {
        return getEffect();
    }
}