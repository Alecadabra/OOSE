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
    public PlayerCharacter(String name, int maxHp, Item inWeapon, Item inArmour,
        int gold, int invSize) throws CharacterException
    {
        super(name, maxHp, gold);
        this.inv = new ItemStorage(invSize);
        this.weapon = inWeapon.getName();
        this.armour = inArmour.getName();
        this.inv.add(inWeapon);
        this.inv.add(inArmour);
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

    public ItemStorage getInventory()
    {
        return inv;
    }

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
    
    public void equip(Item newWeapon)
    {
        if(newWeapon != null && newWeapon.isEquipabble())
        {
            this.weapon = newWeapon;
        }
    }

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

    public String getEquippedName()
    {
        return weapon;
    }
    
    public void wear(Item newArmour)
    {
        if(newArmour != null && newArmour.isEquipabble())
        {
            this.weapon = newArmour;
        }
    }

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
    
    public String getWearingName()
    {
        return armour;
    }
}
