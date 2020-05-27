package Model.Items.Enchantments;

import static java.lang.Math.random;
import static java.lang.Math.round;

import Model.Items.Item;
import Model.Items.ItemException;

/**
 * Enchantment that modifies the damage done to a damage dealing item. Adds a
 * random number between 5 and 10 to the damage.
 */
public class FireDamage extends Enchantment
{
    /**
     * Constructor.
     * @param next The Enchantable Item that this enchantment is being applied
     * to
     */
    public FireDamage(Enchantable next) throws ItemException
    {
        super("Fire Damage", 20, next);
    }

    public Item clone()
    {
        Item clone;

        if(this.next == null)
        {
            try
            {
                clone = new FireDamage(null);
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
                clone = new FireDamage((Enchantable)next.clone());
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
        return next.getDamage() + (int)round(5.0 + random() * 5.0);
    }
}
