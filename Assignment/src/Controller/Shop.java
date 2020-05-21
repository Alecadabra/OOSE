package Controller;

import Model.Items.ShopStorage;

public class Shop
{
    private ShopStorage storage;
    private Character character;

    public Shop(ShopStorage storage, Character character)
    {
        this.storage = storage;
        this.character = character;
    }
}