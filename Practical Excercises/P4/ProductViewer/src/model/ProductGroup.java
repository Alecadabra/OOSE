package model;

import java.util.*;

/**
 * Represents a product group.<CompositeEntry groups are compared on the basis of 
 * their names.
 */
public class ProductGroup extends CompositeEntry
{
    private Set<CompositeEntry> products;
    
    public ProductGroup(String name)
    {
        super(name);
        this.products = new TreeSet<>();
    }
    
    public Set<CompositeEntry> getProducts()
    {
        return Collections.unmodifiableSet(products);
    }
    
    public boolean hasProduct(CompositeEntry p)
    {
        return products.contains(p);
    }
    
    public void addProduct(CompositeEntry p)
    {
        products.add(p);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean eq = false;
        if(obj instanceof ProductGroup)
        {
            eq = name.equals(((ProductGroup)obj).name);
        }
        return eq;
    }
}
