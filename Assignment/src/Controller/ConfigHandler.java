package Controller;

import javax.naming.ConfigurationException;

public class ConfigHandler
{
    public static Object factory(String className, Class<?> superClass)
        throws ConfigException
    {
        Class<?> newClass;
        Object newObj;
        
        try
        {
            newClass = Class.forName(className);
        }
        catch(ClassNotFoundException e)
        {
            throw new ConfigException(
                String.format("Invalid class name %s", className, e));
        }

        if(superClass.isAssignableFrom(newClass))
        {
            newObj = newClass.getDeclaredConstructor().newInstance();
        }
    }
}