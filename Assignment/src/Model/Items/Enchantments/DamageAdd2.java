package Model.Items.Enchantments;

public class DamageAdd2 extends Enchantment
{
    public DamageAdd2(Enchantable next)
    {
        super(
            "Damage +2", // Name
            5, // Cost
            next // Next decoration
        );
    }

    @Override
    public int getDamage()
    {
        return next.getDamage() + 2;
    }
}