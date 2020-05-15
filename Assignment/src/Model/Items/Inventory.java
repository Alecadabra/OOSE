package Model.Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Inventory
{
    private HashMap<String, LinkedList<Item>> inv;
    int maxSize;
    int size;
    
    public Inventory(int maxSize)
    {
        this.inv = new HashMap<>();
        this.maxSize = maxSize;
        this.size = 0;
    }

    public void put(Item item) throws ItemException
    {
        LinkedList<Item> list;
        String key = item.getDescription();

        if(size == maxSize)
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
    }

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

        return item;
    }

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
