package Model.Items.Enchantments;

public class PowerUp extends Enchantment
{
    public PowerUp(Enchantable next)
    {
        super(
            "Power-Up", // Name
            10, // Cost
            next // Next decoration
        );
    }

    @Override
    public int getDamage()
    {
        return (int)((double)next.getDamage() * 1.1);
    }
}
