package Model.Items;

import java.util.ArrayList;

import Controller.ShopLoader;

public class ShopStorage
{
    private ArrayList<Item> items;
    ShopLoader loader;

    public ShopStorage(ShopLoader loader)
    {
        this.items = new ArrayList<>();
        this.loader = loader;
    }

    public void load()
    {
        loader.load(items);
    }
}