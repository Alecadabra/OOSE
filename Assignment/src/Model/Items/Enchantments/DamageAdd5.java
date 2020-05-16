package Model.Items.Enchantments;

/**
 * Enchantment that modifies the damage done to a damage dealing item. Adds 5
 * damage.
 */
public class DamageAdd5 extends Enchantment
{
    /**
     * Constructor.
     * @param next The Enchantable Item that this enchantment is being applied
     * to
     */
    public DamageAdd5(Enchantable next)
    {
        super("Damage +5", 10, next);
    }

    @Override
    public int getDamage()
    {
        return next.getDamage() + 5;
    }
}