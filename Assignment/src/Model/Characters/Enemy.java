package Model.Characters;

import static java.lang.Math.random;

public abstract class Enemy extends Character
{
    protected int minDamage;
    protected int maxDamage;
    protected int minDefence;
    protected int maxDefence;
    
    public Enemy(String name, int maxHp, int minDamage, int maxDamage,
        int minDefence, int maxDefence, int gold)
    {
        super(name, maxHp, gold);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    protected int getBaseDamage()
    {
        return (int)(minDamage + random() * (maxDamage - minDamage));
    }

    @Override
    protected int getDefence()
    {
        return (int)(minDefence + random() * (maxDefence - minDefence));
    }
}