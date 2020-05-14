package Model.Enchantments;

public abstract class Enchantment implements Enchantable
{
    protected Enchantable next;
    private int cost;

    public Enchantment(Enchantable next, int cost)
    {
        this.next = next;
        this.cost = cost;
    }

    public int getCost()
    {
        return cost;
    }
}