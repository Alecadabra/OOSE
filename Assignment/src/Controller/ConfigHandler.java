package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

import View.SimpleCLI;
import View.UserInterface;

public class ConfigHandler
{
    private FileInputStream strm;
    private InputStreamReader rdr;
    private BufferedReader bfr;
    private HashMap<String, String[]> config;

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

    public EnemyFactory getEnemyFactory() throws ConfigException
    {
        EnemyFactory factory = null;
        String[] factoryConfig = getConfig("Enemies");
        String bossName = factoryConfig[0];
        String[] enemies =
            Arrays.copyOfRange(factoryConfig, 1, factoryConfig.length);

        try
        {
            factory = new EnemyFactory(bossName, enemies);
        }
        catch(EnemyFactoryException e)
        {
            throw new ConfigException(
                "Couldn't construct EnemyFactory" + e.getMessage(), e);
        }

        return factory;
    }

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

    public String[] getConfig(String name) throws ConfigException
    {
        String line;
        String[] elem;

        // If config file hasn't been read yet
        if(config.isEmpty())
        {
            try
            {
                line = bfr.readLine();
                elem = line.split(" = ", 1);
                while(line != null)
                {
                    config.put(elem[0], elem[1].split(", "));

                    line = bfr.readLine();
                    elem = line.split(" = ", 1);
                }

                bfr.close();
            }
            catch(IOException e)
            {
                throw new ConfigException(String.format(
                    "Couldn't get configuration for %s; File I/O error; %s",
                    name, e.getMessage()), e);
            }
        }

        if(!config.containsKey(name))
        {
            throw new ConfigException(String.format(
                "Couldn't get configuration for %s; " +
                "Configuration not present in config file", name));
        }

        return config.get(name);
    }

    /*
    public static UserInterface uiFactory(String className)
        throws ConfigException
    {
        Class<?> newClass;
        Class<?> uiSuperClass;
        UserInterface newObj;
        
        try
        {
            uiSuperClass = Class.forName("View.UserInterface");
            newClass = Class.forName(className);
        }
        catch(ClassNotFoundException e)
        {
            throw new ConfigException(
                String.format("Invalid class name %s", className, e));
        }
        catch(ClassCastException e)
        {
            throw new ConfigException(
                String.format("Error creating objects %s", e.getMessage(), e));
        }

        if(uiSuperClass.isAssignableFrom(newClass))
        {
            try
            {
                newObj = (UserInterface)
                    newClass.getDeclaredConstructor().newInstance();
            }
            catch(IllegalAccessException e)
            {
                throw new ConfigException(String.format(
                    "Error creating objects %s", e.getMessage(), e));
            }
            catch(IllegalArgumentException e)
            {
                throw new ConfigException(String.format(
                    "Error creating objects %s", e.getMessage(), e));
            }
            catch(InstantiationException e)
            {
                throw new ConfigException(String.format(
                    "Error creating objects %s", e.getMessage(), e));
            }
            catch(InvocationTargetException e)
            {
                throw new ConfigException(String.format(
                    "Error creating objects %s", e.getMessage(), e));
            }
            catch(NoSuchMethodException e)
            {
                throw new ConfigException(String.format(
                    "Error creating objects %s", e.getMessage(), e));
            }
        }
        else
        {
            throw new ConfigException(String.format(
                "Specified UI class %s is not a UserInterface", className, e));
        }

        return newObj;
    }
     */
}