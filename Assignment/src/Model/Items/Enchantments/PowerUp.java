package Model.Items.Enchantments;

import static java.lang.Math.round;

/**
 * Enchantment that modifies the damage done to a damage dealing item.
 * Multiplies the damage done by 1.1 (rounded to the nearest integer).
 */
public class PowerUp extends Enchantment
{
    /**
     * Constructor.
     * @param next The Enchantable Item that this enchantment is being applied
     * to
     */
    public PowerUp(Enchantable next)
    {
        super("Power-Up", 10, next);
    }

    @Override
    public int getDamage()
    {
        return (int)round((double)next.getDamage() * 1.1);
    }
}
