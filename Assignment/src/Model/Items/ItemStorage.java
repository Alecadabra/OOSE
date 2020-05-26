package Model.Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Model.Items.Enchantments.Enchantment;

public class ItemStorage
{
    private HashMap<String, LinkedList<Item>> hash;
    private LinkedList<Item> equippable;
    private LinkedList<Item> wearable;
    private LinkedList<Item> usable;
    private LinkedList<Item> enchantable;
    private LinkedList<Enchantment> enchantments;
    private int count;
    private int capacity;

    public ItemStorage()
    {
        this.hash = new HashMap<>();
        this.equippable = new LinkedList<>();
        this.wearable = new LinkedList<>();
        this.usable = new LinkedList<>();
        this.enchantable = new LinkedList<>();
        this.enchantments = new LinkedList<>();
        this.count = 0;
        this.capacity = -1;
    }

    public ItemStorage(int capacity)
    {
        this.hash = new HashMap<>();
        this.equippable = new LinkedList<>();
        this.wearable = new LinkedList<>();
        this.usable = new LinkedList<>();
        this.enchantable = new LinkedList<>();
        this.enchantments = new LinkedList<>();
        this.count = 0;
        this.capacity = capacity;
    }

    public ItemStorage(Item... inItems)
    {
        this.hash = new HashMap<>();
        this.equippable = new LinkedList<>();
        this.wearable = new LinkedList<>();
        this.usable = new LinkedList<>();
        this.enchantable = new LinkedList<>();
        this.enchantments = new LinkedList<>();
        this.count = 0;
        this.capacity = -1;

        add(inItems);
    }

    public ItemStorage(int capacity, Item... inItems)
    {
        this.hash = new HashMap<>();
        this.equippable = new LinkedList<>();
        this.wearable = new LinkedList<>();
        this.usable = new LinkedList<>();
        this.enchantable = new LinkedList<>();
        this.enchantments = new LinkedList<>();
        this.count = 0;
        this.capacity = capacity;

        add(inItems);
    }

    @Override
	public ItemStorage clone()
    {
        ItemStorage clone = new ItemStorage(this.capacity);

        for(LinkedList<Item> list : hash.values())
        {
            clone.addCopy((Item[])list.toArray());
        }

        return clone;
    }

    public boolean contains(Item item)
    {
        if(item == null)
        {
            return false;
        }
        else
        {
            return hash.containsKey(item.getName());
        }
    }

    public boolean isFull()
    {
        return (capacity != -1) && (count == capacity);
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public int getCount()
    {
        return count;
    }

    public int getSlots()
    {
        if(capacity == -1)
        {
            return -1;
        }
        else
        {
            return capacity - count
        }
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void add(Item... items)
    {
        LinkedList<Item> list;

        for(Item curr : items)
        {
            if(!isFull())
            {
                if(contains(curr))
                {
                    list = hash.get(curr.getName());
                    list.add(curr);
                }
                else
                {
                    list = new LinkedList<>();
                    list.add(curr);
                    hash.put(curr.getName(), list);
                }

                if(curr.isEquipabble())
                {
                    equippable.add(curr);
                }
                if(curr.isWearable())
                {
                    wearable.add(curr);
                }
                if(curr.isUsable())
                {
                    usable.add(curr);
                }
                if(curr.isEnchantable())
                {
                    enchantable.add(curr);
                }
                if(curr instanceof Enchantment)
                {
                    enchantments.add((Enchantment)curr);
                }

                count++;
            }
        }
    }

    public void addCopy(Item... items)
    {
        LinkedList<Item> list;

        for(Item curr : items)
        {
            if(!isFull())
            {
                if(contains(curr))
                {
                    list = hash.get(curr.getName());
                    list.add(curr.clone());
                }
                else
                {
                    list = new LinkedList<>();
                    list.add(curr.clone());
                    hash.put(curr.getName(), list);
                }

                if(curr.isEquipabble())
                {
                    equippable.add(curr.clone());
                }
                if(curr.isWearable())
                {
                    wearable.add(curr.clone());
                }
                if(curr.isUsable())
                {
                    usable.add(curr.clone());
                }
                if(curr.isEnchantable())
                {
                    enchantable.add(curr.clone());
                }
                if(curr instanceof Enchantment)
                {
                    enchantments.add((Enchantment)curr.clone());
                }

                count++;
            }
        }
    }

    /**
     * Get the Item with the given name. Returns null if not found.
     * @param name The getName() of the Item to lookup
     * @return The Item, or null if not found
     */
    public Item get(String name)
    {
        return hash.get(name).peek();
    }

    /**
     * Get a copy of the Item with the given name. Returns null if not found.
     * @param name The getName() of the Item to lookup
     * @return The Item copy, or null if not found
     */
    public Item getCopy(String name)
    {
        return hash.get(name).peek().clone();
    }

    /**
     * Get the Item with the given name and removes it from storage. Returns
     * null if not found.
     * @param name The getName() of the Item to lookup
     * @return The Item, or null if not found
     */
    public Item remove(String name)
    {
        LinkedList<Item> hashList = hash.get(name);
        Item found = hashList.remove();

        if(found != null)
        {
            if(hashList.isEmpty())
            {
                hash.remove(name);
            }

            if(found.isEquipabble())
            {
                equippable.remove(found);
            }
            if(found.isWearable())
            {
                wearable.remove(found);
            }
            if(found.isUsable())
            {
                usable.remove(found);
            }
            if(found.isEnchantable())
            {
                enchantable.remove(found);
            }
            if(found instanceof Enchantment)
            {
                enchantments.remove((Enchantment)found);
            }

            count--;
        }

        return found;
    }

    /**
     * Gets a list of all stored Items.
     * @return A list containing all stored Items
     */
    public List<Item> getAll()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(curr);
            }
        }

        return all;
    }

    /**
     * Gets a list of copies of all stored Items.
     * @return A list containing a copy of all stored Items
     */
    public List<Item> getAllCopy()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(curr.clone());
            }
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored Items.
     * @return A list containing the getName()'s of all stored Items
     */
    public List<String> getAllNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(curr.getName());
            }
        }

        return all;
    }

    /**
     * Gets a list of all stored equippable Items.
     * @return A list containing all stored equippable Items
     */
    public List<Item> getAllEquippable()
    {
        return equippable;
    }

    /**
     * Gets a list of copies of all stored equippable Items.
     * @return A list containing a copy of all stored equippable Items
     */
    public List<Item> getAllEquippableCopy()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(Item curr : equippable)
        {
            all.add(curr.clone());
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored equippable Items.
     * @return A list containing the getName()'s of all stored equippable Items
     */
    public List<String> getAllEquippableNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : equippable)
        {
            all.add(curr.getName());
        }

        return all;
    }
    
    /**
     * Gets a list of all stored wearable Items.
     * @return A list containing all stored wearable Items
     */
    public List<Item> getAllWearable()
    {
        return wearable;
    }

    /**
     * Gets a list of copies of all stored wearable Items.
     * @return A list containing a copy of all stored wearable Items
     */
    public List<Item> getAllWearableCopy()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(Item curr : wearable)
        {
            all.add(curr.clone());
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored wearable Items.
     * @return A list containing the getName()'s of all stored wearable Items
     */
    public List<String> getAllWearableNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : wearable)
        {
            all.add(curr.getName());
        }

        return all;
    }
        
    /**
     * Gets a list of all stored usable Items.
     * @return A list containing all stored usable Items
     */
    public List<Item> getAllUsable()
    {
        return usable;
    }

    /**
     * Gets a list of copies of all stored usable Items.
     * @return A list containing a copy of all stored usable Items
     */
    public List<Item> getAllUsableCopy()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(Item curr : usable)
        {
            all.add(curr.clone());
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored usable Items.
     * @return A list containing the getName()'s of all stored usable Items
     */
    public List<String> getAllUsableNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : usable)
        {
            all.add(curr.getName());
        }

        return all;
    }
    
    /**
     * Gets a list of all stored enchantable Items.
     * @return A list containing all stored enchantable Items
     */
    public List<Item> getAllEnchantable()
    {
        return enchantable;
    }

    /**
     * Gets a list of copies of all stored enchantable Items.
     * @return A list containing a copy of all stored enchantable Items
     */
    public List<Item> getAllEnchantableCopy()
    {
        LinkedList<Item> all = new LinkedList<>();

        for(Item curr : enchantable)
        {
            all.add(curr.clone());
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored enchantable Items.
     * @return A list containing the getName()'s of all stored enchantable
     * Items
     */
    public List<String> getAllEnchantableNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantable)
        {
            all.add(curr.getName());
        }

        return all;
    }

    /**
     * Gets a list of all stored enchantments.
     * @return A list containing all stored enchantments
     */
    public List<Enchantment> getAllEnchantments()
    {
        return enchantments;
    }

    /**
     * Gets a list of copies of all stored enchantments.
     * @return A list containing a copy of all stored enchantments
     */
    public List<Enchantment> getAllEnchantmentsCopy()
    {
        LinkedList<Enchantment> all = new LinkedList<>();

        for(Item curr : enchantments)
        {
            all.add((Enchantment)curr.clone());
        }

        return all;
    }

    /**
     * Gets a list of the getName()'s of all stored enchantments.
     * @return A list containing the getName()'s of all stored enchantments
     */
    public List<String> getAllEnchantmentsNames()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantments)
        {
            all.add(curr.getName());
        }

        return all;
    }
}