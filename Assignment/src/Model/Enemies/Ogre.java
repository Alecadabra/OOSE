package Model.Enemies;

import static java.lang.Math.random;

public class Ogre extends Enemy
{
    public Ogre(String name, int maxHp, int minDamage, int maxDamage,
    int minDefence, int maxDefence, int gold)
{
    super(
        "Ogre", // Name
        40, // Max health
        5, // Min damage
        10, // Max damage
        6, // Min defence
        12, // Max defence
        40 // Gold awarded upon defeat
    );
}

    @Override
    public int getDamage()
    {
        int dmg;

        if(random() < 0.2)
        {
            // 20% chance that it will attack twice in a row (without the
            // player having a turn in between)
            dmg = getBaseDamage() + getBaseDamage();
            // TODO Notify view
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }    
}