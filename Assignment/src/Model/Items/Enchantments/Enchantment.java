package Model.Items.Enchantments;

import Model.Items.ItemException;

/**
 * Represents an enchantment that can be applied to an enchantable item
 * (Any item that returns true on {@code isEnchantable}) to extends it's
 * functionality when providing defence or dealing damage. Uses the decorator
 * pattern.
 */
public abstract class Enchantment extends Enchantable
{
    protected Enchantable next;

    /**
     * Constructor..
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
            return next.getName();
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
        return String.format("%s [+ %s enchantment]",
            next.getDescription(), name);
    }

    @Override
    public int getCost()
    {
        return next.getCost() + cost;
    }

    @Override
    public int getSell()
    {
        return next.getSell() + getSell();
    }
}
