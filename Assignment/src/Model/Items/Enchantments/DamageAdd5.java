package Model.Items.Enchantments;

import Model.Items.Item;
import Model.Items.ItemException;

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
    public DamageAdd5(Enchantable next) throws ItemException
    {
        super("Damage +5", 10, next);
    }
    
    public Item clone()
    {
        Item clone;

        if(this.next == null)
        {
            try
            {
                clone = new DamageAdd5(null);
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
                clone = new DamageAdd5((Enchantable)next.clone());
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
        return next.getDamage() + 5;
    }
}