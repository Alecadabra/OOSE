package Controller;

import static java.lang.Math.random;

import java.lang.reflect.Constructor;

import Model.Characters.Enemy;

/**
 * Factory class used to generate enemies with {@code getEnemy()}. Probablities
 * of generating different types of enemies change every time the factory is
 * used.
 */
public class EnemyFactory
{
    private int[] enemyProbs;
    private int[] enemyModifiers;
    Constructor<?> bossConstructor;
    Constructor<?>[] enemyConstructors;

    public EnemyFactory(String bossName, String... enemies)
        throws EnemyFactoryException
    {
        try
        {
            // Get default constructor for the boss
            this.bossConstructor = Class.forName(bossName).getConstructor();

            // Initialise arrays based on the number of varargs
            this.enemyConstructors = new Constructor<?>[enemies.length / 3];
            this.enemyProbs = new int[enemies.length / 3];
            this.enemyModifiers = new int[enemies.length / 3];

            // Iterate through varargs (i), setting elements of field arrays (j)
            for(int i = 0, j = 0; i < enemies.length; i += 3, j++)
            {
                // Get default constructor for an enemy
                this.enemyConstructors[j] =
                    Class.forName(enemies[i]).getConstructor();
                // Set the initial probablility for an enemy
                this.enemyProbs[j] = Integer.parseInt(enemies[i + 1]);
                // Set the probability modifier for an enemy
                this.enemyModifiers[j] = Integer.parseInt(enemies[i + 2]);
            }
        }
        catch(NumberFormatException e)
        {
            throw new EnemyFactoryException(String.format(
                "Could not initialise enemy factory; %s", e.getMessage()), e);
        }
        catch(ReflectiveOperationException e)
        {
            throw new EnemyFactoryException(String.format(
                "Could not initialise enemy factory; %s", e.getMessage()), e);
        }
    }

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
}

/*
public class EnemyFactory
{
    private int bossProb;
    private int[] enemyProbs;
    Constructor<?> bossConstructor;
    Constructor<?>[] enemyConstructors;

    public EnemyFactory()
    {
        slimeProb = 50;
        goblinProb = 30;
        ogreProb = 20;
        dragonProb = 0;
    }

    public Enemy getEnemy()
    {
        int rand = (int)(random() * 100);
        Enemy enemy = null;

        try
        {
            Constructor<?> constructor =
                Class.forName("Dragon").getConstructor();
        }
        catch(ReflectiveOperationException e)
        {
            throw new Exception("dfd");
        }

        // Check which enemy to construct
        if(rand < slimeProb)
        {
            enemy = new Slime();
        }
        else if(rand < slimeProb + goblinProb)
        {
            enemy = new Goblin();
        }
        else if(rand < slimeProb + goblinProb + ogreProb)
        {
            enemy = new Ogre();
        }
        else if(rand < slimeProb + goblinProb + ogreProb + dragonProb)
        {
            enemy = new Dragon();
        }

        // Increment probabilites
        if(slimeProb >= 10)
        {
            slimeProb -= 5;
            dragonProb += 5;
        }

        if(goblinProb >= 10)
        {
            goblinProb -= 5;
            dragonProb += 5;
        }

        if(ogreProb >= 10)
        {
            ogreProb -= 5;
            dragonProb += 5;
        }

        return enemy;  
    }
}
*/