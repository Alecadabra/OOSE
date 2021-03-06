package Model.Items.Enchantments;

import Model.Items.ItemException;

/**
 * Represents an enchantment that can be applied to an enchantable item
 * (Any item that returns true on {@code isEnchantable()}) to extend it's
 * functionality when providing defence, dealing damage or healing. Uses the
 * decorator pattern.
 */
public abstract class Enchantment extends Enchantable
{
    protected Enchantable next;

    /**
     * Constructor.
     * @param name Name of the enchantment
     * @param cost Enchantment's cost in gold when bought
     * @param next The enchantable that this is enchanting (Set to null
     * if just a display item)
     * @throws ItemException If an error occured
     */
    public Enchantment(String name, int cost, Enchantable next)
        throws ItemException
    {
        super(name, cost, 0, 0);
        this.next = next;
    }

    @Override
    public String getName()
    {
        if(this.next == null)
        {
            return super.getName();
        }
        else
        {
            return String.format("%s [%s]",
                next.getName(), name);
        }
    }
    
    /**
     * Gets the description string of an enchantable item, consisting of the
     * enchanted item's description with the enchantment's name appended, eg:
     * {@code <Original item description> [+ Fire-Damage enchantment]}.
     * @return Description string
     */
    @Override
    public String getDescription()
    {
        if(this.next == null)
        {
            return getName();
        }
        else
        {
            return String.format("%s [%s]",
                next.getDescription(), name);
        }
    }

    @Override
    public int getCost()
    {
        if(this.next == null)
        {
            return cost;
        }
        else
        {
            return next.getCost() + cost;
        }
    }

    @Override
    public int getSell()
    {
        if(this.next == null)
        {
            return super.getSell();
        }
        else
        {
            return next.getSell() + super.getSell();
        }
    }

    @Override
    public boolean isEnchantable()
    {
        if(next == null)
        {
            // If this is just a display enchantment in a shop we don't want
            // it messing up anything
            return false;
        }
        else
        {
            // Enchantments themselves can be enchanted (Decorator pattern)
            return true;
        }
    }

    @Override
    public boolean isEquipabble()
    {
        if(next == null)
        {
            return false;
        }
        else
        {
            return next.isEquipabble();
        }
    }

    @Override
    public boolean isWearable()
    {
        if(next == null)
        {
            return false;
        }
        else
        {
            return next.isWearable();
        }
    }

    @Override
    public boolean isUsable()
    {
        if(next == null)
        {
            return false;
        }
        else
        {
            return next.isUsable();
        }
    }
}
