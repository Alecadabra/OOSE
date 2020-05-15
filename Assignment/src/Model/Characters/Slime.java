package Model.Characters;

import static java.lang.Math.random;

public class Slime extends Enemy
{
    public Slime(String name, int maxHp, int minDamage, int maxDamage,
        int minDefence, int maxDefence, int gold)
    {
        super(
            "Slime", // Name
            10, // Max health
            5, // Min damage
            3, // Max damage
            0, // Min defence
            2, // Max defence
            10 // Gold awarded upon defeat
        );
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