package Model.Items;

public class DamagePotion extends Potion
{
    public DamagePotion(String name, int cost, int minDamage, int maxDamage)
    {
        super(name, cost, minDamage, maxDamage);
    }

    @Override
    public int getDamage()
    {
        return getEffect();
    }
}