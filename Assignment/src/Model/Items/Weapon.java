package Model.Items;

import Model.Enchantments.Enchantable;

public class Weapon extends Item implements Enchantable
{
    private String weaponType;
    private String damageType;
    
    public Weapon(String name, int cost, int minEffect, int maxEffect,
        String weaponType, String damageType)
    {
        super(name, cost, minEffect, maxEffect);
        this.weaponType = weaponType;
        this.damageType = damageType;
    }

    @Override
    public String getDescription()
    {
        return String.format("%s (%s, %s damage)",
            name, weaponType, damageType);
    }

    @Override
    public int getDamage()
    {
        return getEffect();
    }
}
