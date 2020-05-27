package Controller;

import static java.lang.Math.random;

import java.lang.reflect.Constructor;

import Model.Characters.Enemy;

/**
 * Factory class used to generate enemies with {@code getEnemy()}. Probablities
 * of generating different types of enemies change every time the factory is
 * used. Uses reflective constructors.
 */
public class EnemyFactory
{
    Constructor<?> bossConstructor;
    Constructor<?>[] enemyConstructors;
    private int[] enemyProbs;
    private int[] enemyModifiers;

    /**
     * Constructor.
     * @param bossConstructor The java.lang.reflect.Constructor<?> of the boss
     * of the game (Game ends if defeated)
     * @param enemyConstructors An array of java.lang.reflect.Constructor<?>'s
     * for each of the standard enemies
     * @param enemyProbs An array of integers from 0 to 100, representing the
     * initial probability of each enemy being created on each getEnemy() call.
     * Each entry corresponds to a constructor in enemyConstructors
     * @param enemyModifiers An array of integers, representing the change to
     * an enemies probability of being created on each subsequent call to
     * getEnemy. For example, a value of {@code -5} would correspond to a 5%
     * decrease in the enemies chance to be created, and a 5% increase to the
     * bosses probability. Each entry corresponds to a constructor in
     * enemyConstructors
     * @throws EnemyFactoryException
     */
    public EnemyFactory(Constructor<?> bossConstructor,
        Constructor<?>[] enemyConstructors, int[] enemyProbs,
        int[] enemyModifiers) throws EnemyFactoryException
    {
        this.bossConstructor = bossConstructor;
        this.enemyConstructors = enemyConstructors;
        this.enemyProbs = enemyProbs;
        this.enemyModifiers = enemyModifiers;
    }

    /**
     * Gets a randomly selected enemy based on the current probabilites.
     * @return The new enemy
     * @throws EnemyFactoryException If an error occured with the constructor
     */
    public Enemy getEnemy() throws EnemyFactoryException
    {
        int rand = (int)(random() * 100);
        Enemy enemy = null;
        int prob = 0;

        // Check which enemy to construct
        for(int i = 0; i < enemyConstructors.length && enemy == null; i++)
        {
            prob += enemyProbs[i];

            if(rand < prob)
            {
                // Construct this enemy
                try
                {
                    enemy = (Enemy)enemyConstructors[i].newInstance();
                }
                catch(ReflectiveOperationException e)
                {
                    throw new EnemyFactoryException(String.format(
                        "Could not create enemy; %s", e.getMessage()), e);
                }
            }
        }

        // Hoo boy here we go it's boss time
        if(enemy == null)
        {
            try
            {
                enemy = (Enemy)bossConstructor.newInstance();
            }
            catch(ReflectiveOperationException e)
            {
                throw new EnemyFactoryException(String.format(
                    "Could not create boss enemy; %s", e.getMessage()), e);
            }
        }
        
        // Decrement enemy probablilities
        for(int i = 0; i < enemyConstructors.length; i++)
        {
            enemyProbs[i] += enemyModifiers[i];
        }
        
        return enemy;  
    }

    /**
     * Checks if an enemy is the boss enemy.
     * @param enemy Enemy to check
     * @return True if the specified enemy is the boss
     */
    public boolean isBoss(Enemy enemy)
    {
        return bossConstructor.getDeclaringClass().equals(enemy.getClass());
    }
}