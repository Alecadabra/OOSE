package Model.Items;

import java.util.ArrayList;

import Controller.EnchantmentHandler;
import Controller.EnchantmentHandlerException;
import Controller.ShopLoader;
import Controller.ShopLoaderException;

public class ShopStorage
{
    private ArrayList<Item> items;
    ShopLoader loader;
    EnchantmentHandler enchantHandler;

    /**
     * Constructor.
     * @param loader ShopLoader implementation to use
     * @param enchantHandler EnchantmentHandler object
     */
    public ShopStorage(ShopLoader loader, EnchantmentHandler enchantHandler)
    {
        this.items = new ArrayList<>();
        this.loader = loader;
        this.enchantHandler = enchantHandler;
    }

    /**
     * Loads items from the shoploader and display enchantments from the
     * enchantment handler.
     * @throws ItemException If an error occured
     */
    public void load() throws ItemException
    {
        try
        {
            loader.load(items);
            enchantHandler.load(items);
        }
        catch(ShopLoaderException | EnchantmentHandlerException e)
        {
            throw new ItemException(e.getMessage(), e);
        }
    }

    /**
     * Gets the enchantment handler held by this class so that it can be used.
     * @return The enchantment handler
     */
    public EnchantmentHandler getEnchantmentHandler()
    {
        return enchantHandler;
    }
}
