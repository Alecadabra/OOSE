package Model.Items.Enchantments;

import Model.Items.Item;
import Model.Items.ItemException;

public class NullEnchantable extends Enchantable
{
    public NullEnchantable() throws ItemException
    {
        super("NULL", 0, 0, 0);
    }

    @Override
    public Item clone()
    {
        try
        {
            return new NullEnchantable();
        }
        catch(ItemException e) {}
        return null;
    }
}