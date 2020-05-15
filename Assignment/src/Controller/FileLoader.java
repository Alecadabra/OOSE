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

/**
 * Extends a ShopLoader to iterate over a file of Items. Items must be
 * separated by newlines and attributes must be separated by a comma and a
 * space. Eg. {@code W, Short Sword, 5, 9, 10, slashing, Sword} for a weapon
 * called {@code Short Sword} with 5-9 damage, cost of 10 gold,
 * {@code slashing} damage and type {@code Sword}.
 */
public class FileLoader extends ShopLoader
{
    private FileInputStream strm;
    private InputStreamReader rdr;
    private BufferedReader bfr;
    private String line;
    private String[] elem;

    /**
     * Constructor, opens file and reads first line
     * @param fileName Name of file to read from
     * @throws ShopLoaderException If an error occured in I/O
     */
    public FileLoader(String fileName) throws ShopLoaderException
    {
        super();
        try
        {
            this.strm = new FileInputStream(fileName);
            this.rdr = new InputStreamReader(strm);
            this.bfr = new BufferedReader(rdr);
            readLine();
        }
        catch(IOException e)
        {
            throw new ShopLoaderException("File I/O error", e);
        }
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

    /**
     * Remove is not supported
     * @throws UnsupportedOperationException When called
     */
    @Override
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /**
     * Reads attributes of a weapon, parses values and constructs weapon.
     * @return The new weapon
     */
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

            if(verifyStringAttr(name) &&
               verifyEffectRange(minDamage, maxDamage) &&
               verifyCost(cost) &&
               verifyStringAttr(damageType) &&
               verifyStringAttr(weaponType))
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

    /**
     * Reads attributes of a piece of armour, parses values and constructs
     * the armour.
     * @return The new armour
     */
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

            if(verifyStringAttr(name) &&
               verifyEffectRange(minDefence, maxDefence) &&
               verifyCost(cost) &&
               verifyStringAttr(material))
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

    /**
     * Reads attributes of a potion, parses values and constructs either a
     * health or damage potion.
     * @return The new potion
     */
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

            if(verifyStringAttr(name) &&
               verifyEffectRange(minEffect, maxEffect) &&
               verifyCost(cost) &&
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

    /**
     * Reads the next line in the file.
     * @throws IOException If an IOException occured
     */
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
            bfr.close();
        }
    }
}