package Model.Items;

import Model.Items.Enchantments.Enchantable;

/**
 * Represents a weapon that deals damage to another character when attacking if
 * equipped. A weapon can be enchanted to add functionality.
 */
public class Weapon extends Enchantable
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
    public Weapon(String name, int cost, int minDamage, int maxDamage,
        String weaponType, String damageType)
    {
        super(name, cost, minDamage, maxDamage);
        this.weaponType = weaponType;
        this.damageType = damageType;
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
}
