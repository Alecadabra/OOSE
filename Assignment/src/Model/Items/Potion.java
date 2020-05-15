package Model.Items;

public abstract class Potion extends Item
{
    public Potion(String name, int cost, int minEffect, int maxEffect)
    {
        super(name, cost, minEffect, maxEffect);
    }
}
