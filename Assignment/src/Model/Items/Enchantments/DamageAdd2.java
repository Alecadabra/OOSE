package Model.Items.Enchantments;

/**
 * Enchantment that modifies the damage done to a damage dealing item. Adds 2
 * damage.
 */
public class DamageAdd2 extends Enchantment
{
    /**
     * Constructor.
     * @param next The Enchantable Item that this enchantment is being applied
     * to
     */
    public DamageAdd2(Enchantable next)
    {
        super("Damage +2", 5, next);
    }

    @Override
    public int getDamage()
    {
        return next.getDamage() + 2;
    }
}