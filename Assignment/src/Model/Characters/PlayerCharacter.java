package Model.Characters;

import java.util.Arrays;
import java.util.List;

import Model.Items.Item;
import Model.Items.ItemStorage;

/**
 * Represents a playable character. Is a character that has an inventory of
 * items, and has a weapon and armour equipped.
 */
public class PlayerCharacter extends GameCharacter
{
    private ItemStorage inv;
    private String weapon;
    private String armour;

    /**
     * Constructor.
     * @param name Name of player character
     * @param maxHp Maximum possible health
     * @param inWeapon Starting weapon
     * @param inArmour Starting armour
     * @param gold Gold in posession
     * @param invSize Maximum capacity of inventory
     */
    public PlayerCharacter(String name, int maxHp, ItemStorage inv,
        Item inWeapon, Item inArmour, int gold) throws CharacterException
    {
        super(name, maxHp, gold);
        this.inv = inv; // Dependency Injection! :)
        this.inv.add(inWeapon);
        this.inv.add(inArmour);
        this.weapon = inWeapon.getName();
        this.armour = inArmour.getName();
    }

    /**
     * Get the damage dealt by this character for an attack. Uses the damage of
     * the currently equipped damage item.
     * @return Damage integer
     */
    @Override
    protected int getDamage()
    {
        return inv.get(weapon).getDamage();
    }

    /**
     * Get the defence of this character for defending agaisnt an attack. Uses
     * the defence of the currently worn defence item.
     * @return Defence integer
     */
    @Override
    protected int getDefence()
    {
        return inv.get(armour).getDefence();
    }

    /**
     * Gets the inventory (ItemStorage object) of this player. Gives the
     * original reference, rather than a copy, to simplify operations.
     * @return The ItemStorage reference
     */
    public ItemStorage getInventory()
    {
        return inv;
    }

    /**
     * Set hp to zero :(.
     */
    public void kill()
    {
        this.hp = 0;
	}

    /**
     * Get a list of the player's attributes to show in the user interface.
     * @return List<String> of name, health, gold, weapon and armour
     */
    public List<String> getAttributes()
    {
        return Arrays.asList(
            "Name: " + this.name,
            "Health: " + this.hp + "/" + this.maxHp,
            "Gold: " + this.gold,
            "Weapon: " + inv.get(this.weapon).getDescription(),
            "Armour: " + inv.get(this.armour).getDescription()
        );
    }
    
    /**
     * Equips an equippable item, has no effect if item is not equippable
     * @param newWeapon The Item to equip
     */
    public void equip(Item newWeapon)
    {
        if(newWeapon != null && newWeapon.isEquipabble())
        {
            this.weapon = newWeapon.getName();
        }
    }

    /**
     * Checks if a given item is currently equipped
     * @param item The item to check
     * @return True if, and only if, the item is equipped
     */
    public boolean isEquipped(Item item)
    {
        if(this.weapon.equals(item.getName()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the getName() of the currently equipped item
     * @return Name string of the item
     */
    public String getEquippedName()
    {
        return weapon;
    }
    
    /**
     * Wears an wearable item, has no effect if item is not wearable
     * @param newArmour The Item to wear
     */
    public void wear(Item newArmour)
    {
        if(newArmour != null && newArmour.isWearable())
        {
            this.weapon = newArmour.getName();
        }
    }

    /**
     * Checks if a given item is currently being worn
     * @param item The item to check
     * @return True if, and only if, the item is being worn
     */
    public boolean isWearing(Item item)
    {
        if(this.armour.equals(item.getName()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Gets the getName() of the currently worn item
     * @return Name string of the item
     */
    public String getWearingName()
    {
        return armour;
    }
}
