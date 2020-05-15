package Model.Items.Enchantments;

public abstract class Enchantment extends Enchantable
{
    protected Enchantable next;

    public Enchantment(String name, int cost, Enchantable next)
    {
        super(name, cost, 0, 0);
        this.next = next;
    }

    @Override
    public int getCost()
    {
        return next.getCost() + cost;
    }

    @Override
    public int getSell()
    {
        return next.getSell() + getSell();
    }
}