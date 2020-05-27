package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.NoSuchElementException;

import Model.Characters.PlayerCharacter;
import Model.Characters.CharacterException;
import Model.Items.DefaultArmour;
import Model.Items.Item;
import Model.Items.ItemException;
import Model.Items.ItemStorage;
import Model.Items.Enchantments.Enchantable;
import Model.Items.DefaultWeapon;
import View.SimpleCLI;
import View.UserInterface;

/**
 * Class to handle configuration file. Reads the file and builds a map
 * of configuration settings to use when it's many factory methods are used.
 * Has factory methods for EnemyFactory, PlayerCharacter, Starting Weapon,
 * Starting Armour, UserInterface, ShopLoader & EnchantmentHandler.
 */
public class ConfigHandler
{
    private FileInputStream strm;
    private InputStreamReader rdr;
    private BufferedReader bfr;
    private HashMap<String, String[]> config;

    /**
     * Constructor.
     * @param fileName File name of the configuration file
     * @throws ConfigException If a File I/O error occured
     */
    public ConfigHandler(String fileName) throws ConfigException
    {
        try
        {
            this.strm = new FileInputStream(fileName);
        }
        catch(IOException e)
        {
            throw new ConfigException("File I/O Error; " + e.getMessage(), e);
        }

        this.rdr = new InputStreamReader(strm);
        this.bfr = new BufferedReader(rdr);
        this.config = new HashMap<>();
    }

    /**
     * Get the EnemyFactory object specified by the config file
     * @return New EnemyFactory object
     * @throws ConfigException If an error occured
     */
    public EnemyFactory getEnemyFactory() throws ConfigException
    {
        EnemyFactory factory = null;
        String[] factoryConfig = getConfig("Enemies");
        int numEnemies = (factoryConfig.length - 1) / 3;
        Constructor<?> bossConstructor;
        Constructor<?>[] enemyConstructors = new Constructor<?>[numEnemies];
        int[] enemyProbs = new int[numEnemies];
        int[] enemyModifiers = new int[numEnemies];

        try
        {
            bossConstructor = (Constructor<?>)
               Class.forName(factoryConfig[0]).getConstructor();

            for(int i = 0, j = 1; i < numEnemies; i++, j += 3)
            {
                enemyConstructors[i] = (Constructor<?>)
                    Class.forName(factoryConfig[j]).getConstructor();
                enemyProbs[i] = Integer.parseInt(factoryConfig[j + 1]);
                enemyModifiers[i] = Integer.parseInt(factoryConfig[j + 2]);
            }

            factory = new EnemyFactory(bossConstructor, enemyConstructors, 
                enemyProbs, enemyModifiers);
        }
        catch(NumberFormatException | ReflectiveOperationException |
            EnemyFactoryException e)
        {
            throw new ConfigException("Couldn't construct EnemyFactory; " +
                "Invalid enemy config (boss, enemy 1 name, " +
                "enemy 1 initial probability, " +
                "enemy 1 probability modifier, enemy 2 name, ...); "+
                e.getMessage(), e);
        }

        return factory;
    }

    /**
     * Get the PlayerCharacter object specified by the config file
     * @return New PlayerCharacter object
     * @throws ConfigException If an error occured
     */
    public PlayerCharacter getPlayerCharacter() throws ConfigException
    {
        PlayerCharacter player = null;
        String[] playerConfig = getConfig("Player");

        String name;
        int maxHp, gold;
        Item inWeapon, inArmour;
        ItemStorage inv;

        try
        {
            name = playerConfig[0];
            maxHp = Integer.parseInt(playerConfig[1]);
            inWeapon = getStartingWeapon();
            inArmour = getStartingArmour();
            gold = Integer.parseInt(playerConfig[2]);
            inv = new ItemStorage(Integer.parseInt(playerConfig[3]));

            player = new PlayerCharacter(
                name, maxHp, inv, inWeapon, inArmour, gold);
        }
        catch(NumberFormatException | IndexOutOfBoundsException e)
        {
            // Accessing array or parsing ints threw an exception
            throw new ConfigException("Couldn't construct player; " +
                "Invalid player config (name, max health, starting gold); " +
                e.getMessage(), e);
        }
        catch(ConfigException e)
        {
            // getStarting*() threw an exception
            throw new ConfigException("Couldn't construct player; " +
                e.getMessage(), e);
        }
        catch(CharacterException e)
        {
            // PlayerCharacter constructor threw an exception
            throw new ConfigException("Couldn't construct player; " +
                e.getMessage(), e);
        }

        return player;
    }

    /**
     * Get the Starting Weapon Item object specified by the config file
     * @return New Item object
     * @throws ConfigException If an error occured
     */
    public Item getStartingWeapon() throws ConfigException
    {
        Item item = null;
        String[] itemConfig = getConfig("Starting Weapon");

        String name;
        int cost, minEffect, maxEffect;

        switch(itemConfig[0])
        {
            case "W":
            {
                // Default weapon
                String weaponType, damageType;

                try
                {
                    name = itemConfig[1];
                    minEffect = Integer.parseInt(itemConfig[2]);
                    maxEffect = Integer.parseInt(itemConfig[3]);
                    cost = Integer.parseInt(itemConfig[4]);
                    damageType = itemConfig[5];
                    weaponType = itemConfig[6];

                    item = new DefaultWeapon(name, cost, minEffect, maxEffect,
                        weaponType, damageType);
                }
                catch(NumberFormatException | IndexOutOfBoundsException | 
                    ItemException e)
                {
                    // Accessing array or parsing ints threw an exception
                    throw new ConfigException(
                        "Couldn't construct starting weapon; " +
                        "Invalid weapon config (item type, name, cost, " +
                        "min damage, max damage, weapon type, damage type); " +
                        e.getMessage(), e);
                }
            }
        }

        if(item == null)
        {
            throw new ConfigException(
                "Couldn't construct starting weapon; "+
                "Invalid weapon config item type");
        }

        return item;
    }

    /**
     * Get the Starting Armour Item object specified by the config file
     * @return New Item object
     * @throws ConfigException If an error occured
     */
    public Item getStartingArmour() throws ConfigException
    {
        Item item = null;
        String[] itemConfig = getConfig("Starting Armour");

        String name;
        int cost, minEffect, maxEffect;

        switch(itemConfig[0])
        {
            case "A":
            {
                // Default armour
                String material;

                try
                {
                    name = itemConfig[1];
                    minEffect = Integer.parseInt(itemConfig[2]);
                    maxEffect = Integer.parseInt(itemConfig[3]);
                    cost = Integer.parseInt(itemConfig[4]);
                    material = itemConfig[5];

                    item = new DefaultArmour(name, cost, minEffect, maxEffect,
                        material);
                }
                catch(NumberFormatException | IndexOutOfBoundsException | 
                    ItemException e)
                {
                    // Accessing array or parsing ints threw an exception
                    throw new ConfigException(
                        "Couldn't construct starting armour; " +
                        "Invalid armour config (item type, name, cost, " +
                        "min damage, max damage, material); " +
                        e.getMessage(), e);
                }
            }
        }

        if(item == null)
        {
            throw new ConfigException(
                "Couldn't construct starting armour; "+
                "Invalid armour config item type");
        }

        return item;
    }

    /**
     * Get the UserInterface object specified by the config file
     * @return New UserInterface object
     * @throws ConfigException If an error occured
     */
    public UserInterface getUI() throws ConfigException
    {
        UserInterface ui = null;
        String[] uiConfig = getConfig("UserInterface");

        switch(uiConfig[0])
        {
            case "SimpleCLI":
            {
                ui = new SimpleCLI();
                break;
            }
        }

        if(ui == null)
        {
            throw new ConfigException(String.format(
                "Couldn't construct UserInterface %s; "+
                "File is not a valid UserInterface", uiConfig[0]));
        }

        return ui;
    }

    /**
     * Get the ShopLoader object specified by the config file
     * @return New ShopLoader object
     * @throws ConfigException If an error occured
     */
    public ShopLoader getShopLoader() throws ConfigException
    {
        ShopLoader loader = null;
        String[] loaderConfig = getConfig("ShopLoader");

        switch(loaderConfig[0])
        {
            case "FileShopLoader":
            {
                try
                {
                    loader = new FileShopLoader(loaderConfig[1]);
                }
                catch(NoSuchElementException e)
                {
                    throw new ConfigException(String.format(
                        "Couldn't construct ShopLoader %s; %s",
                        loaderConfig[0], e.getMessage()), e);
                }
            }            
        }

        if(loader == null)
        {
            throw new ConfigException(String.format(
                "Couldn't construct ShopLoader %s; "+
                "File is not a valid ShopLoader", loaderConfig[0]));
        }

        return loader;
    }

    /**
     * Get the EnchantmentHandler object specified by the config file
     * @return New EnchantmentHandler object
     * @throws ConfigException If an error occured
     */
    public EnchantmentHandler getEnchantmentHandler() throws ConfigException
    {
        EnchantmentHandler handler = null;
        String[] handlerConfig = getConfig("Enchantments");

        Constructor<?>[] constructors;

        try
        {
            constructors = new Constructor<?>[handlerConfig.length];

            for(int i = 0; i < constructors.length; i++)
            {
                constructors[i] = (Constructor<?>)
                    Class.forName(handlerConfig[i])
                    .getConstructor(Enchantable.class);
            }

            handler = new EnchantmentHandler(constructors);
        }
        catch(ReflectiveOperationException e)
        {
            String enchantsString = "";
            for(int i = 0; i < handlerConfig.length; i++)
            {
                enchantsString += handlerConfig[i];
                if(i + 1 != handlerConfig.length)
                {
                    enchantsString += ", ";
                }
            }
            throw new ConfigException(String.format(
                "Couldn't construct EnchantmentHandler for enchantments " +
                "(%s); %s", enchantsString, e.getMessage()), e);
        }

        return handler;
    }

    /**
     * Gets the configuration for the specified name as an array of strings.
     * Eg. For the line in the config file {@code Player = playerName, 20, 50,
     * 15}, {@code getConfig("Player")} would return an array of {@code 
     * ("playerName", "20", "50", "15")}.
     * If this is the first time calling getConfig(), the file will be read.
     * @param name Name of the configuration
     * @return The String array of the config for this name
     * @throws ConfigException If an error occured
     */
    public String[] getConfig(String name) throws ConfigException
    {
        String line;
        String[] elem;
        String[] configElem;

        // If config file hasn't been read yet
        if(config.isEmpty())
        {
            try
            {
                line = bfr.readLine();
                while(line != null)
                {
                    elem = line.split(" = ", 2);

                    // Make sure line isnt a comment or whitespace
                    if(!(line.equals("") || line.charAt(0) == '#'))
                    {
                        // Remove spaces and make lowercase before keying
                        elem[0] = elem[0].toLowerCase().replaceAll(" ", "");

                        if(!elem[1].contains(","))
                        {
                            // If it's just a single element config
                            configElem = new String[] {elem[1]};
                        }
                        else
                        {
                            // If it's a tuple of configurations
                            configElem = elem[1].split(",");
                        }

                        for(int i = 0; i < configElem.length; i++)
                        {
                            // Remove surrounding whitespace
                            configElem[i] = configElem[i].trim();
                        }

                        config.put(elem[0], configElem);
                    }

                    line = bfr.readLine();
                }

                bfr.close();
            }
            catch(IOException e)
            {
                throw new ConfigException(String.format(
                    "Couldn't get configuration for %s; File I/O error; %s",
                    name, e.getMessage()), e);
            }
            catch(NullPointerException | IndexOutOfBoundsException e)
            {
                throw new ConfigException(String.format(
                    "Couldn't get configuration for %s; " +
                    "Invalid config file format", name), e);
            }
        }

        name = name.toLowerCase().replaceAll(" ", "");

        if(!config.containsKey(name))
        {
            throw new ConfigException(String.format(
                "Couldn't get configuration for %s; " +
                "Configuration not present in config file", name));
        }

        // Remove spaces and make lowercase before keying
        return config.get(name);
    }
}
