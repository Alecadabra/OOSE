package Model.Enemies;

import static java.lang.Math.random;

public abstract class Enemy
{
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int minDamage;
    protected int maxDamage;
    protected int minDefence;
    protected int maxDefence;
    protected int gold;
    
    public Enemy(String name, int maxHp, int minDamage, int maxDamage,
        int minDefence, int maxDefence, int gold)
    {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.gold = gold;
    }

    public abstract int getDamage();

    protected int getBaseDamage()
    {
        return (int)(minDamage + random() * (maxDamage - minDamage));
    }

    public int getDefence()
    {
        return (int)(minDefence + random() * (maxDefence - minDefence));
    }
}