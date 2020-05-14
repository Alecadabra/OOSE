package Model.Enchantments;

public class DamageAdd5 extends Enchantment
{
    public DamageAdd5(Enchantable next)
    {
        super(next, 10); // Costs 10 gold
    }

    @Override
    public int getEffect()
    {
        return next.getEffect() + 5;
    }
}