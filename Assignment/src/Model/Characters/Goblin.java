package Model.Characters;

import static java.lang.Math.random;

/**
 * Represents a Goblin Enemy. Has 30 health, 3-8 damage, 4-8 defence, 20 gold.
 * Special ability: 50% chance that its attack will have 3 extra damage.
 */
public class Goblin extends Enemy
{
    /**
     * Construct a new Goblin.
     */
    public Goblin()
    {
        super("Goblin", 30, 3, 8, 4, 8, 20);
    }

    @Override
    protected int getDamage()
    {
        int dmg;

        if(random() < 0.5)
        {
            // Special ability: 50% chance that its attack will have 3
            // extra damage
            dmg = getBaseDamage() + 3;
            logAction("Goblin used special ability: Increase damage!");
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }    
}