package Model.Enchantments;

public class PowerUp extends Enchantment
{
    public PowerUp(Enchantable next)
    {
        super(next, 10); // Costs 10 gold
    }

    @Override
    public int getEffect()
    {
        return (int)((double)next.getEffect() * 1.1);
    }
}