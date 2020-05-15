package Model.Items.Enchantments;

public class DamageAdd5 extends Enchantment
{
    public DamageAdd5(Enchantable next)
    {
        super(
            "Damage +5", // Name
            10, // Cost
            next // Next decoration
        );
    }

    @Override
    public int getDamage()
    {
        return next.getDamage() + 5;
    }
}