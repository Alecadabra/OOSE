package Model.Characters;

import java.util.List;

import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.ItemException;

/**
 * Represents a playable character. Is a character that has an inventory of
 * items, and has a weapon and armour equipped.
 */
public class PlayerCharacter extends Character
{
    private Inventory inv;
    private String weapon;
    private String armour;

    /**
     * Constructor.
     * @param name Name of player character
     * @param maxHp Maximum possible health
     * @param inWeapon Starting weapon
     * @param inArmour Starting armour
     * @param gold Gold in posession
     */
    public PlayerCharacter(String name, int maxHp, Item inWeapon, Item inArmour,
        int gold)
    {
        super(name, maxHp, gold);
        this.inv = new Inventory(15); // Max size of 15
        this.weapon = inWeapon.getDescription();
        this.armour = inArmour.getDescription();
        try
        {
            inv.add(inWeapon);
            inv.add(inArmour);
        }
        catch(ItemException e)
        {
            // TODO notify view
        }
    }

    /**
     * Get the damage dealt by this character for an attack. Uses the damage of
     * the currently equipped weapon.
     * @return Damage integer
     */
    @Override
    protected int getDamage()
    {
        int damage;

        try
        {
            damage = inv.get(weapon).getDamage();
        }
        catch(ItemException e)
        {
            // TODO notify view
            damage = 0;
        }

        return damage;
    }

    /**
     * Get the defence of this character for defending agaisnt an attack. Uses
     * the defence of the currently equipped armour.
     * @return Defence integer
     */
    @Override
    protected int getDefence()
    {
        int defence;
        
        try
        {
            defence = inv.get(armour).getDefence();
        }
        catch(ItemException e)
        {
            // TODO notify view
            defence = 0;
        }

        return defence;
    }

    /**
     * Add an item to the inventory.
     * @param item Item to add
     */
    public void addItem(Item item)
    {
        try
        {
            inv.add(item);
        }
        catch(ItemException e)
        {
            // TODO notify view
        }
    }

    /**
     * Remove an item from the inventory and return it.
     * @param desc The getDescription() of the item to remove
     * @return Item removed
     */
    public Item removeItem(String desc)
    {
        Item item;

        try
        {
            item = inv.remove(desc);
        }
        catch(ItemException e)
        {
            // TODO notify view
            item = null;
        }

        return item;
    }

    /**
     * Get an item from the inventory.
     * @param desc The getDescription() of the item to remove
     * @return The item
     */
    public Item getItem(String desc)
    {
        Item item;

        try
        {
            item = inv.get(desc);
        }
        catch(ItemException e)
        {
            // TODO notify view
            item = null;
        }

        return item;
    }

    /**
     * Get an (unmodifiable) list of all items in inventory, with duplicates
     * @return Unmodifiable list
     */
    public List<Item> getAllItems()
    {
        return inv.getAll();
    }
}
