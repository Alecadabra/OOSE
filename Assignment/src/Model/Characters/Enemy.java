package Model.Characters;

import static java.lang.Math.random;
import static java.lang.Math.round;

/**
 * Represents a computer-controlled enemy character.
 */
public abstract class Enemy extends GameCharacter
{
    protected int minDamage;
    protected int maxDamage;
    protected int minDefence;
    protected int maxDefence;

    /**
     * Constructor.
     * @param name Name of enemy
     * @param maxHp Maximum possible health
     * @param minDamage Minimum value that this item can return on getDamage()
     * before spcial abilities
     * @param maxDamage Maximum value that this item can return on getDamage()
     * before spcial abilities
     * @param minDefence Minimum value that this item can return on getDefence()
     * before spcial abilities
     * @param maxDefence Maximum value that this item can return on getDefence()
     * before spcial abilities
     * @param gold Gold in posession
     */
    public Enemy(String name, int maxHp, int minDamage, int maxDamage,
        int minDefence, int maxDefence, int gold)
    {
        super(name, maxHp, gold);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    /**
     * Gets the damage dealt by this enemy, a randomly generated number
     * between minDamage and maxDamage. If this character has a special ability
     * that effects damage dealt, this number will be effected as defined in
     * the overriden getDamage().
     * @return Damage integer
     */
    protected int getBaseDamage()
    {
        return (int)round(minDamage + random() * (maxDamage - minDamage));
    }

    /**
     * Gets the damage dealt by this enemy, a randomly generated number
     * between minDamage and maxDamage. Override this method in the extending
     * class to define a special ability, using getBaseDamage() as the base
     * damage.
     * @return Damage integer
     */
    @Override
    protected int getDamage()
    {
        return getBaseDamage();
    }

    /**
     * Gets the defence of this enemy, a randomly generated number between
     * minDefence and maxDefence. If this character has a special ability
     * that effects defence, this number will be effected as defined in
     * the overriden getDefence().
     * @return Defence integer
     */
    protected int getBaseDefence()
    {
        return (int)round(minDefence + random() * (maxDefence - minDefence));
    }

    /**
     * Gets the defence of this enemy, a randomly generated number between
     * minDefence and maxDefence. Override this method in the extending
     * class to define a special ability, using getBaseDefence() as the base
     * defence.
     * @return Defence integer
     */
    @Override
    protected int getDefence()
    {
        return getBaseDefence();
    }
}