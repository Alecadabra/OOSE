package Model.Characters;

import static java.lang.Math.random;

public class Dragon extends Enemy
{
    public Dragon(String name, int maxHp, int minDamage, int maxDamage,
    int minDefence, int maxDefence, int gold)
    {
        super(
            "Dragon", // Name
            100, // Max health
            15, // Min damage
            30, // Max damage
            15, // Min defence
            20, // Max defence
            100 // Gold awarded upon defeat
        );
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