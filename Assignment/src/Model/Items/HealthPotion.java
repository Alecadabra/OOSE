package Model.Items;

public class HealthPotion extends Potion
{
    public HealthPotion(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }

    @Override
    public int getHealing()
    {
        return getEffect();
    }
}