package Model.Items.Enchantments;

import static java.lang.Math.round;

import Model.Items.Item;
import Model.Items.ItemException;

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
    public PowerUp(Enchantable next) throws ItemException
    {
        super("Power-Up", 10, next);
    }

    public Item clone()
    {
        Item clone;

        if(this.next == null)
        {
            try
            {
                clone = new PowerUp(null);
            }
            catch(ItemException e)
            {
                // Shouldn't ever happen
                clone = null;
            }
        }
        else
        {
            try
            {
                clone = new PowerUp((Enchantable)next.clone());
            }
            catch(ItemException e)
            {
                // Shouldn't ever happen
                clone = null;
            }
        }

        return clone;
    }

    /**
     * Get the damage dealt, uses the decorator pattern to modify the 'next's'
     * damage.
     * @return The damage dealt
     */
    @Override
    public int getDamage()
    {
        return (int)round((double)next.getDamage() * 1.1);
    }
}
