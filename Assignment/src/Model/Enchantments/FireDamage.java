package Model.Enchantments;

import static java.lang.Math.random;

public class FireDamage extends Enchantment
{
    public FireDamage(Enchantable next)
    {
        super(next, 20); // Costs 20 gold
    }

    @Override
    public int getEffect()
    {
        return next.getEffect() + (int)(5.0 + random() * 5.0);
    }    
}