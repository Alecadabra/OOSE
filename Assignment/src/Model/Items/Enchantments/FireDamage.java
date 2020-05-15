package Model.Items.Enchantments;

import static java.lang.Math.random;

public class FireDamage extends Enchantment
{
    public FireDamage(Enchantable next)
    {
        super(
            "Fire Damage", // Name
            20, // Cost
            next // Next decoration
        );
    }

    @Override
    public int getDamage()
    {
        return next.getDamage() + (int)(5.0 + random() * 5.0);
    }
}
