package Model.Characters;

import java.util.List;

import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.ItemException;

/**
 * Represents a playable character. Is a character that has an inventory of
 * items, and has a weapon and armour equipped.
 */
public class PlayerCharacter extends GameCharacter
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
        int gold) throws CharacterException
    {
        super(name, maxHp, gold);
        this.inv = new Inventory(15); // TODO make inv size based on config
        this.weapon = inWeapon.getDescription();
        this.armour = inArmour.getDescription();
        try
        {
            inv.add(inWeapon);
            inv.add(inArmour);
        }
        catch(ItemException e)
        {
            throw new CharacterException("Could not create character; " +
                e.getMessage(), e);
        }
    }

    /**
     * Get the damage dealt by this character for an attack. Uses the damage of
     * the currently equipped damage item.
     * @return Damage integer
     * @throws CharacterException If an error occured
     */
    @Override
    protected int getDamage() throws CharacterException
    {
        int damage;

        try
        {
            damage = inv.get(weapon).getDamage();
        }
        catch(ItemException e)
        {
            throw new CharacterException(
                "Could not get damage for equipped damage item; " +
                e.getMessage(), e);
        }

        return damage;
    }

    /**
     * Get the defence of this character for defending agaisnt an attack. Uses
     * the defence of the currently worn defence item.
     * @return Defence integer
     * @throws CharacterException If an error occured
     */
    @Override
    protected int getDefence() throws CharacterException
    {
        int defence;
        
        try
        {
            defence = inv.get(armour).getDefence();
        }
        catch(ItemException e)
        {
            throw new CharacterException(
                "Could not get defence for worn defence item; " +
                e.getMessage(), e);
        }

        return defence;
    }

    /**
     * Add an item to the inventory.
     * @param item Item to add
     * @throws CharacterException If an error occured
     */
    public void addItem(Item item) throws CharacterException
    {
        try
        {
            inv.add(item);
        }
        catch(ItemException e)
        {
            throw new CharacterException(
                "Could not add item to inventory; " + e.getMessage(), e);
        }
    }

    /**
     * Remove an item from the inventory and return it.
     * 
     * @param desc The getDescription() of the item to remove
     * @return Item removed
     * @throws CharacterException If an error occured
     */
    public Item removeItem(String desc) throws CharacterException
    {
        Item item;

        try
        {
            item = inv.remove(desc);
        }
        catch(ItemException e)
        {
            throw new CharacterException(
                "Could not remove item from inventory; " + e.getMessage(), e);
        }

        return item;
    }

    /**
     * Get an item from the inventory.
     * 
     * @param desc The getDescription() of the item to remove
     * @return The item
     * @throws CharacterException If an error occured
     */
    public Item getItem(String desc) throws CharacterException
    {
        Item item;

        try
        {
            item = inv.get(desc);
        }
        catch(ItemException e)
        {
            throw new CharacterException(
                "Could not get item from inventory; " + e.getMessage(), e);
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
