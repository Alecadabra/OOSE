package Model.Enemies;

import static java.lang.Math.random;

public class Goblin extends Enemy
{
    public Goblin(String name, int maxHp, int minDamage, int maxDamage,
    int minDefence, int maxDefence, int gold)
{
    super(
        "Goblin", // Name
        30, // Max health
        3, // Min damage
        8, // Max damage
        4, // Min defence
        8, // Max defence
        20 // Gold awarded upon defeat
    );
}

    @Override
    public int getDamage()
    {
        int dmg;

        if(random() < 0.5)
        {
            // Special ability: 50% chance that its attack will have 3
            // extra damage
            dmg = getBaseDamage() + 3;
            // TODO Notify view
        }
        else
        {
            dmg = getBaseDamage();
        }

        return dmg;
    }    
}