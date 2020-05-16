package Model.Items;

import java.util.ArrayList;

import Controller.ShopLoader;
import Controller.ShopLoaderException;

public class ShopStorage
{
    private ArrayList<Item> items;
    ShopLoader loader;

    public ShopStorage(ShopLoader loader)
    {
        this.items = new ArrayList<>();
        this.loader = loader;
    }

    public void load() throws ItemException
    {
        try
        {
            loader.load(items);
        }
        catch(ShopLoaderException e)
        {
            throw new ItemException(e.getMessage(), e);
        }
    }
}