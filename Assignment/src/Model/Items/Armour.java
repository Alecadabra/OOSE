package Model.Items;

public class Armour extends Item
{
    String material;
    
    public Armour(String name, int cost, int minEffect, int maxEffect,
        String material)
    {
        super(name, cost, minEffect, maxEffect);
        this.material = material;
    }

    @Override
    public String getDescription()
    {
        return String.format("%s (%s)", name, material);
    }

    @Override
    public int getDefence()
    {
        return getEffect();
    }
}