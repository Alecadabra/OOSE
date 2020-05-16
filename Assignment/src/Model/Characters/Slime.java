package Model.Characters;

import static java.lang.Math.random;

/**
 * Represents a Slime Enemy. Has 10 health, 3-5 damage, 0-2 defence, 10 gold.
 * Special ability: 20% chance that its attack will have no damage.
 */
public class Slime extends Enemy
{
    /**
     * Construct a new Slime.
     */
    public Slime()
    {
        super("Slime", 10, 3, 5, 0, 2, 10);
    }

    @Override
    protected int getDamage()
    {
        int dmg;

        if(random() < 0.2)
        {
            // Special ability: 20% chance that its attach will have no damage
            dmg = 0;
            // TODO Notify view
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }
}