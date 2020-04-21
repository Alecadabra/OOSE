package model;

import java.util.Objects;

/**
 * Represents a product. Products have names, prices and numbers-in-stock, but 
 * are compared on the basis of their names only.
 */
public class Product implements Comparable<Product>
{
    private String name;
    private float price;
    private int numberInStock;
    
    public Product(String name, float price, int numberInStock)
    {
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getPrice()
    {
        return price;
    }
    
    public int getNumberInStock()
    {
        return numberInStock;
    }
    
    @Override 
    public boolean equals(Object obj)
    {
        boolean eq = false;
        if(obj instanceof Product)
        {
            eq = name.equals(((Product)obj).name);
        }
        return eq;
    }
    
    @Override 
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override public int compareTo(Product p)
    {
        return name.compareTo(p.name);
    }    
}
