package Model.Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * The inventory of a player character, stores Items
 */
public class Inventory
{
    private HashMap<String, LinkedList<Item>> inv;
    int maxSize;
    int count;
    
    /**
     * Constructor.
     * @param maxSize Maximum number of items that can be stored in inventory
     */
    public Inventory(int maxSize)
    {
        this.inv = new HashMap<>();
        this.maxSize = maxSize;
        this.count = 0;
    }

    /**
     * Add a new item to the inventory
     * @param item The new item
     * @throws ItemException If the inventory is full
     */
    public void add(Item item) throws ItemException
    {
        LinkedList<Item> list;
        String key = item.getDescription();

        if(count == maxSize)
        {
            throw new ItemException("Inventory full");
        }
        
        if(!inv.containsKey(key))
        {
            // Not a duplicate item - Make a new list at this key and add item
            list = new LinkedList<>();
            list.add(item);
            inv.put(key, list);
        }
        else
        {
            // Duplicate item - Add new item to list at this key
            list = inv.get(key);
            list.add(item);
        }

        count++;
    }

    /**
     * Get an item from the inventory without removing it.
     * @param key The getDescription() of the item
     * @return The item matching the description
     * @throws ItemException If the item does not exist
     */
    public Item get(String key) throws ItemException
    {
        Item item;
        LinkedList<Item> list;

        if(!inv.containsKey(key))
        {
            throw new ItemException("No such item in inventory");
        }

        list = inv.get(key);
        item = list.peek();

        return item;
    }

    /**
     * Get an item from the inventoyr and remove it
     * @param key The getDescription() of the item
     * @return The item matching the description
     * @throws ItemException If the item does not exist
     */
    public Item remove(String key) throws ItemException
    {
        Item item;
        LinkedList<Item> list;

        if(!inv.containsKey(key))
        {
            throw new ItemException("No such item in inventory");
        }

        list = inv.get(key);
        item = list.remove();

        count--;

        return item;
    }

    /**
     * Get an (unmodifiable) list of all items in inventory, with duplicates
     * @return Unmodifiable List<Item>
     */
    public List<Item> getAll()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(String key : inv.keySet())
        {
            for(Item item : inv.get(key))
            {
                all.add(item);
            }
        }

        return Collections.unmodifiableList(all);
    }
}
