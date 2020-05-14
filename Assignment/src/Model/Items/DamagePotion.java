package Model.Items;

public class DamagePotion extends Potion
{
    public DamagePotion(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }

    @Override
    public int getDamage()
    {
        return getEffect();
    }
}