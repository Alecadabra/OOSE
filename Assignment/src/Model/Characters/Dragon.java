package Model.Characters;

import static java.lang.Math.random;

/**
 * Represents a Dragon Enemy. Has 30 health, 3-8 damage, 4-8 defence, 20 gold.
 * Special ability: 50% chance that its attack will have 3 extra damage.
 */
public class Dragon extends Enemy
{
    /**
     * Construct a new Dragon.
     */
    public Dragon()
    {
        super("Dragon", 100, 15, 30, 15, 20, 100);
    }

    @Override
    protected int getDamage()
    {
        int dmg;
        double randVal = random();

        if(randVal < 0.35)
        {
            /* Special ability: 35% chance of one of the following happening
             * when it attacks:
               (a) damage inflicted will double (25% chance), or
               (b) it will recover 10 health (10% chance). */
            if(randVal < 0.25)
            {
                // Damage inflicted will double (25% chance)
                dmg = getBaseDamage() * 2;
                // TODO Notify view
            }
            else
            {
                // It will recover 10 health (10% chance)
                dmg = getBaseDamage();
                heal(10);
                // TODO Notify view

            }
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }    
}