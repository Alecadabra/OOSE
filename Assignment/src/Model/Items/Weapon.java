package Model.Items;

import Model.Items.Enchantments.Enchantable;

public class Weapon extends Enchantable
{
    private String weaponType;
    private String damageType;
    
    public Weapon(String name, int cost, int minDamage, int maxDamage,
        String weaponType, String damageType)
    {
        super(name, cost, minDamage, maxDamage);
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
