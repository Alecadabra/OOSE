package Model.Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Model.Items.Enchantments.Enchantment;

/**
 * Data structure for storing Items. Has a high memory overhead but provides
 * fairly efficient and elegant retreival methods for each different type of
 * Item. Can be configured with a maximum size.
 */
public class ItemStorage
{
    // Items mapped by name, with duplicates added to the list.
    private HashMap<String, LinkedList<Item>> hash;

    // Linked lists for each unique type of item for quick acces
    private LinkedList<Item> equippable;
    private LinkedList<Item> wearable;
    private LinkedList<Item> usable;
    private LinkedList<Item> enchantable;
    private LinkedList<Enchantment> enchantments;

    // Keep track of size
    private int count;
    private int capacity;

    /**
     * Default constructor. Creates empty ItemStorage with no fixed size.
     */
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

    /**
     * ALternate constructor. Creates empty ItemStorage with the given fixed
     * size.
     * @param capacity The maximum number of items that can be stored
     */
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

    /**
     * Alternate constructor. Creates ItemStorage populated with the items
     * given with no fixed size.
     * @param inItems Varargs (Or array) of Items to add
     */
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

    /**
     * Alternate constructor. Creates ItemStorage populated with the items
     * given with given fixed size.
     * @param capacity The maximum number of items that can be stored
     * @param inItems Varargs (Or array) of Items to add
     */
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

    /**
     * Checks if the given item is contained in the storage.
     * @param item The item to check
     * @return True if, and only if, the item is stored
     */
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

    /**
     * Checks if the ItemStorage is full. If the ItemStorage is not configured
     * to have a fixed size, this always returns false.
     * @return True if, and only if, there is a fixed capacity and it has
     * been reached
     */
    public boolean isFull()
    {
        return (capacity != -1) && (count == capacity);
    }

    /**
     * Checks if the ItemStorage is empty.
     * @return True if, and only if, there are no items stored.
     */
    public boolean isEmpty()
    {
        return count == 0;
    }

    /**
     * Gets the number of items contained.
     * @return The number of items
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Gets the number of free slots that the storage has before it is full. If
     * it is not configured to have a fixed size then it returns -1.
     * @return The number of free slots remaining, or -1 if size not fixed
     */
    public int getSlots()
    {
        if(capacity == -1)
        {
            return -1;
        }
        else
        {
            return capacity - count;
        }
    }

    /**
     * Gets the capacity that the storage has. If it is not configured to have
     * a fixed size then it returns -1.
     * @return The capacity, or -1 if size not fixed
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * Adds one or more items to storage.
     * @param items Varargs (Or array) of items to add
     */
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

    /**
     * Adds clones of one or more items to storage.
     * @param items Varargs (Or array) of items to add
     */
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
     * Gets a list of the getDescriptions()'s of all stored Items.
     * @return A list containing the getDescription()'s of all stored Items
     */
    public List<String> getAllDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(curr.getDescription());
            }
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and costs of all stored Items.
     * Eg. an entry could be {@code Short Sword (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored items
     */
    public List<String> getAllCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(String.format("%s (%d gold)",
                    curr.getDescription(), curr.getCost()));
            }
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored
     * Items. Eg. an entry could be {@code Short Sword (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored items
     */
    public List<String> getAllSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(LinkedList<Item> list : hash.values())
        {
            for(Item curr : list)
            {
                all.add(String.format("%s (Sell for %d gold)",
                    curr.getDescription(), curr.getCost()));
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
     * Gets a list of the geDescription()'s of all stored equippable Items.
     * @return A list containing the getDescription()'s of all stored
     * equippable Items
     */
    public List<String> getAllEquippableDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : equippable)
        {
            all.add(curr.getDescription());
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and costs of all stored equippable
     * Items. Eg. an entry could be {@code Short Sword (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored
     * equippable items
     */
    public List<String> getAllEquippableCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : equippable)
        {
            all.add(String.format("%s (%d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored 
     * equippable Items. Eg. an entry could be
     * {@code Short Sword (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored equippable items
     */
    public List<String> getAllEquippableSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : equippable)
        {
            all.add(String.format("%s (Sell for %d gold)",
                curr.getDescription(), curr.getCost()));
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
     * Gets a list of the getDescription()'s of all stored wearable Items.
     * @return A list containing the getDescription()'s of all stored wearable Items
     */
    public List<String> getAllWearableDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : wearable)
        {
            all.add(curr.getDescription());
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and costs of all stored wearable
     * Items. Eg. an entry could be {@code Chain Mail (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored
     * wearable items
     */
    public List<String> getAllWearableCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : wearable)
        {
            all.add(String.format("%s (%d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored
     * wearable Items. Eg. an entry could be {@code Chain Mail (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored wearable items
     */
    public List<String> getAllWearableSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : wearable)
        {
            all.add(String.format("%s (Sell for %d gold)",
                curr.getDescription(), curr.getCost()));
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
     * Gets a list of the getDescription()'s of all stored usable Items.
     * @return A list containing the getDescription()'s of all stored usable
     * Items
     */
    public List<String> getAllUsableDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : usable)
        {
            all.add(curr.getDescription());
        }

        return all;
    }
    
    /**
     * Gets a list of the getDescription()'s and costs of all stored usable
     * Items. Eg. an entry could be {@code Health Potion (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored
     * usable items
     */
    public List<String> getAllUsableCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : usable)
        {
            all.add(String.format("%s (%d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored
     * usable Items. Eg. an entry could be {@code Health Potion (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored usable items
     */
    public List<String> getAllUsableSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : usable)
        {
            all.add(String.format("%s (Sell for %d gold)",
                curr.getDescription(), curr.getCost()));
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
     * Gets a list of the getDescription()'s of all stored enchantable Items.
     * @return A list containing the getDescription()'s of all stored
     * enchantable Items
     */
    public List<String> getAllEnchantableDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantable)
        {
            all.add(curr.getDescription());
        }

        return all;
    }
    
    /**
     * Gets a list of the getDescription()'s and costs of all stored
     * enchantable Items. Eg. an entry could be {@code Short Sword (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored
     * enchantable items
     */
    public List<String> getAllEnchantableCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantable)
        {
            all.add(String.format("%s (%d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored
     * enchantable Items. Eg. an entry could be {@code Short Sword (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored enchantable items
     */
    public List<String> getAllEnchantableSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantable)
        {
            all.add(String.format("%s (Sell for %d gold)",
                curr.getDescription(), curr.getCost()));
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
    
    /**
     * Gets a list of the getDescription()'s of all stored enchantments.
     * @return A list containing the getDescription()'s of all stored
     * enchantments
     */
    public List<String> getAllEnchantmentsDescriptions()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantments)
        {
            all.add(curr.getDescription());
        }

        return all;
    }
        
    /**
     * Gets a list of the getDescription()'s and costs of all stored
     * enchantments. Eg. an entry could be {@code Fire Damage (10 gold)}.
     * @return A list containing the getDescription()'s and costs of all stored
     * enchantments
     */
    public List<String> getAllEnchantmentsCosts()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantments)
        {
            all.add(String.format("%s (%d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

    /**
     * Gets a list of the getDescription()'s and sell prices of all stored
     * enchantments Eg. an entry could be {@code Fire Damage (Sells for 5 gold)}.
     * @return A list containing the getDescription()'s and sell prices of all
     * stored enchantments
     */
    public List<String> getAllEnchantmentsSells()
    {
        LinkedList<String> all = new LinkedList<>();

        for(Item curr : enchantments)
        {
            all.add(String.format("%s (Sell for %d gold)",
                curr.getDescription(), curr.getCost()));
        }

        return all;
    }

}