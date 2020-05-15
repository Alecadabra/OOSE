package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Items.Item;

public abstract class ShopLoader implements Iterator<Item>
{
    public void load(ArrayList<Item> list)
    {
        setup();
        //do potions too

    }

    protected boolean verifyName(String name)
    {
        return name.equals("");
    }

    protected boolean verifyEffectRange(int min, int max)
    {
        return min >= 0 && max >= 0 && min <= max;
    }

    protected boolean verifyCost(int cost)
    {
        return cost >= 0;
    }
}