package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Model.Items.Armour;
import Model.Items.DamagePotion;
import Model.Items.HealthPotion;
import Model.Items.Item;
import Model.Items.Potion;
import Model.Items.Weapon;

public class FileLoader extends ShopLoader
{
    private FileInputStream strm;
    private InputStreamReader rdr;
    private BufferedReader bfr;
    private String line;
    private String fileName;
    private String[] elem;

    public FileLoader(String fileName) throws ShopLoaderException
    {
        try
        {
            this.strm = new FileInputStream(fileName);
            this.rdr = new InputStreamReader(strm);
            this.bfr = new BufferedReader(rdr);
            readLine();
        }
        catch(IOException e)
        {
            throw new ShopLoaderException("File IO error", e);
        }

        this.fileName = fileName;
    }

    @Override
    public boolean hasNext()
    {
        return elem != null;
    }

    @Override
    public Item next()
    {
        Item item;

        switch(line.charAt(0))
        {
            case 'W': item = readWeapon();
            case 'A': item = readArmour();
            case 'P': item = readPotion();
            default:
            {
                // TODO notify view
                item = null;
            }
        }

        try
        {
            readLine();
        }
        catch(IOException e)
        {
            // TODO notify view
            elem = null;
            line = null;
        }

        return item;
    }

    private Weapon readWeapon()
    {
        Weapon weapon = null;
        String name;
        int minDamage, maxDamage;
        int cost;
        String damageType, weaponType;

        try
        {
            name = elem[1];
            minDamage = Integer.parseInt(elem[2]);
            maxDamage = Integer.parseInt(elem[3]);
            cost = Integer.parseInt(elem[4]);
            damageType = elem[5];
            weaponType = elem[6];

            if(verifyName(name) &&
               verifyEffectRange(minDamage, maxDamage) &&
               verifyCost(cost) &&
               verifyName(damageType) &&
               verifyName(weaponType))
            {
                weapon = new Weapon(name, cost, minDamage, maxDamage,
                    weaponType, damageType);
            }
            else
            {
                // TODO notify view
            }
        }
        catch(NumberFormatException e)
        {
            // TODO notify view
        }

        return weapon;
    }

    private Armour readArmour()
    {
        Armour armour = null;
        String name;
        int minDefence, maxDefence;
        int cost;
        String material;

        try
        {
            name = elem[1];
            minDefence = Integer.parseInt(elem[2]);
            maxDefence = Integer.parseInt(elem[3]);
            cost = Integer.parseInt(elem[4]);
            material = elem[5];

            if(verifyName(name) &&
               verifyEffectRange(minDefence, maxDefence) &&
               verifyCost(cost) &&
               verifyName(material))
            {
                armour = new Armour(name, cost, minDefence, maxDefence,
                    material);
            }
            else
            {
                // TODO notify view
            }
        }
        catch(NumberFormatException e)
        {
            // TODO notify view
        }

        return armour;
    }

    private Potion readPotion()
    {
        Potion potion = null;
        String name;
        int minEffect, maxEffect;
        int cost;
        char type;

        try
        {
            name = elem[1];
            minEffect = Integer.parseInt(elem[2]);
            maxEffect = Integer.parseInt(elem[3]);
            cost = Integer.parseInt(elem[4]);
            type = elem[5].charAt(0);

            if(verifyName(name) &&
               verifyEffectRange(minEffect, maxEffect) &&
               verifyCost(cost) &&
               verifyName(material) &&
               (type == 'H' || type == 'D'))
            {
                if(type == 'H')
                {
                    potion = new HealthPotion(name, cost, minEffect,
                        maxEffect);
                }
                else
                {
                    potion = new DamagePotion(name, cost, minEffect,
                        maxEffect);
                }
            }
            else
            {
                // TODO notify view 
            }
        }
        catch(NumberFormatException e)
        {
            // TODO notify view
        }

        return potion;
    }

    private void readLine() throws IOException
    {
        line = bfr.readLine();
        if(line != null)
        {
            elem = line.split(", ");
        }
        else
        {
            elem = null;
        }
    }
}