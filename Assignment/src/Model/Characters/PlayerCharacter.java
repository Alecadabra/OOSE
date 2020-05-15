package Model.Characters;

import java.util.List;

import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.ItemException;

public class PlayerCharacter extends Character
{
    private Inventory inv;
    private String weapon;
    private String armour;

    public PlayerCharacter(String name, int maxHp, Item inWeapon, Item inArmour,
        int gold)
    {
        super(name, maxHp, gold);
        this.inv = new Inventory(15); // Max size of 15
        this.weapon = inWeapon.getDescription();
        this.armour = inArmour.getDescription();
        try
        {
            inv.put(inWeapon);
            inv.put(inArmour);
        }
        catch(ItemException e)
        {
            // TODO notify view
        }
    }

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

    public void addItem(Item item)
    {
        try
        {
            inv.put(item);
        }
        catch(ItemException e)
        {
            // TODO notify view
        }
    }

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

    public List<Item> getAllItems()
    {
        return inv.getAll();
    }
}
