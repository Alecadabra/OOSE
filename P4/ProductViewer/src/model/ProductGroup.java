package model;

import java.util.*;

/**
 * Represents a product group. Product groups are compared on the basis of 
 * their names.
 */
public class ProductGroup implements Comparable<ProductGroup>
{
    private String name;
    private Set<Product> products;
    
    public ProductGroup(String name)
    {
        this.name = name;
        this.products = new TreeSet<>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public Set<Product> getProducts()
    {
        return Collections.unmodifiableSet(products);
    }
    
    public boolean hasProduct(Product p)
    {
        return products.contains(p);
    }
    
    public void addProduct(Product p)
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
    
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override
    public int compareTo(ProductGroup group)
    {
        return name.compareTo(group.name);
    }
}
