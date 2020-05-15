package Model.Items.Enchantments;

import Model.Items.Item;

public abstract class Enchantable extends Item
{
    public Enchantable(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }
}
