package Model.Items;

import static java.lang.Math.random;

public abstract class Item
{
    protected String name;
    protected int cost;
    protected int minEffect;
    protected int maxEffect;

    public Item(String name, int cost, int minEffect, int maxEffect)
    {
        this.name = name;
        this.cost = cost;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return name;
    }

    public int getCost()
    {
        return cost;
    }

    public int getSell()
    {
        return cost / 2;
    }

    protected int getEffect()
    {
        return (int)(minEffect + random() * (maxEffect - minEffect));
    }

    public int getDamage()
    {
        return 0;
    }

    public int getHealing()
    {
        return 0;
    }

}