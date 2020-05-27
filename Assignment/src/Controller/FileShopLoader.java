package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import Model.Items.DefaultArmour;
import Model.Items.DamagePotion;
import Model.Items.HealthPotion;
import Model.Items.Item;
import Model.Items.ItemException;
import Model.Items.DefaultPotion;
import Model.Items.DefaultWeapon;

/**
 * Extends a ShopLoader to iterate over a file of Items. Items must be
 * separated by newlines and attributes must be separated by a comma and a
 * space. Configured to read DefaultWeapon ("W"), DefaultArmour ("A"),
 * DefaultPotion ("P", with "D" for damage or "H" for healh). Eg. {@code W, 
 * Short Sword, 5, 9, 10, slashing, Sword} for a default weapon
 * called {@code Short Sword} with 5-9 damage, cost of 10 gold,
 * {@code slashing} damage and type {@code Sword}.
 */
public class FileShopLoader extends ShopLoader
{
    private FileInputStream strm;
    private InputStreamReader rdr;
    private BufferedReader bfr;
    private String line;
    private String[] elem;

    /**
     * Constructor, opens file and reads first line.
     * @param fileName Name of file to read from
     * @throws NoSuchElementException If an error occured in I/O
     */
    public FileShopLoader(String fileName) throws NoSuchElementException
    {
        super();
        try
        {
            this.strm = new FileInputStream(fileName);
        }
        catch(IOException e)
        {
            throw new NoSuchElementException(
                "File I/O error opening input file; " + e.getMessage());
        }

        this.rdr = new InputStreamReader(strm);
        this.bfr = new BufferedReader(rdr);
        readLine();
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

        // Select the item type
        switch(elem[0])
        {
            case "W":
            {
                // W = Default weapon
                item = readDefaultWeapon();
                break;
            }
            case "A":
            {
                // A = Default armour
                item = readDefaultArmour();
                break;
            }
            case "P":
            {
                // P = Default potion
                item = readPotion();
                break;
            }
            default:
            {
                throw new NoSuchElementException(String.format(
                    "Invalid values in input file (item type = %s)", elem[0]));
            }
        }

        // Read next line
        readLine();
        
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
     * Reads attributes of a Default Weapon Item, parses values and constructs
     * weapon.
     * @return The new default weapon
     */
    private DefaultWeapon readDefaultWeapon() throws NoSuchElementException
    {
        DefaultWeapon weapon = null;
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

            weapon = new DefaultWeapon(name, cost, minDamage, maxDamage,
                weaponType, damageType);
        }
        catch(NumberFormatException | IndexOutOfBoundsException | 
            ItemException e)
        {
            throw new NoSuchElementException("Invalid values in input file; " +
                e.getMessage());
        }

        return weapon;
    }

    /**
     * Reads attributes of a Default Armour Item, parses values and constructs
     * the armour.
     * @return The new default armour
     */
    private DefaultArmour readDefaultArmour() throws NoSuchElementException
    {
        DefaultArmour armour = null;
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


            armour = new DefaultArmour(name, cost, minDefence, maxDefence,
                material);
        }
        catch(NumberFormatException | IndexOutOfBoundsException | 
            ItemException e)
        {
            throw new NoSuchElementException("Invalid values in input file; " +
                e.getMessage());
        }

        return armour;
    }

    /**
     * Reads attributes of a potion, parses values and constructs either a
     * health or damage potion.
     * @return The new potion
     */
    private DefaultPotion readPotion()
    {
        DefaultPotion potion = null;
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
            type = Character.toUpperCase(elem[5].charAt(0));

            if(type == 'H')
            {
                potion = new HealthPotion(name, cost, minEffect,
                    maxEffect);
            }
            else if(type == 'D')
            {
                potion = new DamagePotion(name, cost, minEffect,
                    maxEffect);
            }
            else
            {
                // Invalid potion type
                throw new ItemException(String.format(
                    "Invalid value for potion (type = %c)", type));
            }

        }
        catch(NumberFormatException | IndexOutOfBoundsException | 
            ItemException e)
        {
            throw new NoSuchElementException("Invalid values in input file; " +
                e.getMessage());
        }

        return potion;
    }

    /**
     * Reads the next line in the file.
     * @throws NoSuchElementException If an I/O error occured
     */
    private void readLine() throws NoSuchElementException
    {
        try
        {
            line = bfr.readLine();
        }
        catch(IOException e)
        {
            throw new NoSuchElementException(
                "File I/O error reading input file; " + e.getMessage());
        }

        if(line != null)
        {
            elem = line.split(",");
            for(int i = 0; i < elem.length; i++)
            {
                elem[i] = elem[i].trim();
            }
        }
        else
        {
            elem = null;
            try
            {
                bfr.close();
            }
            catch(IOException e)
            {
                try
                {
                    strm.close();
                }
                catch(IOException ee) { /* Nothing to do */ }

                throw new NoSuchElementException(
                    "File I/O error closing input file; " + e.getMessage());
            }
        }
    }
}