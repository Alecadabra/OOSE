package Model.Characters;

import static java.lang.Math.random;

/**
 * Represents an Ogre Enemy. Has 40 health, 5-10 damage, 6-12 defence, 40 gold.
 * Special ability: 20% chance that it will attack twice in one attack.
 */
public class Ogre extends Enemy
{
    /**
     * Construct a new Ogre.
     */
    public Ogre()
    {
        super("Ogre", 40, 5, 10, 6, 12, 40);
    }

    @Override
    protected int getDamage()
    {
        int dmg;

        if(random() < 0.2)
        {
            // 20% chance that it will attack twice in a row (without the
            // other character having a turn in between)
            dmg = getBaseDamage() + getBaseDamage();
            logAction("Ogre used special ability: Double attack!");
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }    
}
