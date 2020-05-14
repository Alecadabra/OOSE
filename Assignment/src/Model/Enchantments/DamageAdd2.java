package Model.Enchantments;

public class DamageAdd2 extends Enchantment
{
    public DamageAdd2(Enchantable next)
    {
        super(next, 5); // Costs 5 gold
    }

    @Override
    public int getEffect()
    {
        return next.getEffect() + 2;
    }
}