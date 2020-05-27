package Model.Items;

import Model.Items.Enchantments.Enchantable;

/**
 * Default implementation of a Weapon (Defined as an equippable Item that
 * should override getDamage in some way). Default Weapon's deal damage in the
 * damage range given, have vanity string attributes for type of weapon and 
 * damage, and can be enchanted to add functionality.
 */
public class DefaultWeapon extends Enchantable
{
    private String weaponType;
    private String damageType;

    /**
     * Constructor.
     * @param name Name of the weapon
     * @param cost Weapon's cost in gold when bought
     * @param minDefence Minimum value that this item can return on getDamage()
     * @param maxDefence Maximum value that this item can return on getDamage()
     * @param weaponType Type of weapon that this is (Has no effect)
     * @param damageType Type of damage this weapon deals (Has no effect)
     */
    public DefaultWeapon(String name, int cost, int minDamage, int maxDamage,
        String weaponType, String damageType) throws ItemException
    {
        super(name, cost, minDamage, maxDamage);
        if(verifyStringAttr(weaponType) &&
            verifyStringAttr(damageType))
        {
            this.weaponType = weaponType;
            this.damageType = damageType;
        }
        else
        {
            throw new ItemException(String.format("Invalid values for weapon " +
                "(weapon type = %s, damage type = %s)",
                weaponType, damageType));
        }
    }

    public Item clone()
    {
        Item clone;

        try
        {
            clone = new DefaultWeapon(
                name, cost, minEffect, maxEffect, weaponType, damageType);
        }
        catch(ItemException e)
        {
            // Should never happen
            clone = null;
        }

        return clone;
    }

    /**
     * Gets the description string of a weapon, consisting of it's name,
     * weapon type, damage and damage type, eg:
     * {@code Short Sword (Sword, 5-9 slashing damage)}.
     * @return Description string
     */
    @Override
    public String getDescription()
    {
        return String.format("%s (%s, %d-%d %s damage)",
            name, weaponType, minEffect, maxEffect, damageType);
    }

    /**
     * Gets the damage dealt by this weapon, a randomly generated number
     * between minDamage and maxDamage.
     * @return Damage integer
     */
    @Override
    public int getDamage()
    {
        return getEffect();
    }

    /**
     * Determines if an item can be equipped as a damage dealing item by a
     * player character.
     * @return True if equipabble, false otherwise
     */
    @Override
    public boolean isEquipabble()
    {
        return true;
    }
}
