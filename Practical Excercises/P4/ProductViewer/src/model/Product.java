package model;

import java.util.Objects;

/**
 * Represents a product. Products have names, prices and numbers-in-stock, but 
 * are compared on the basis of their names only.
 */
public class Product extends CompositeEntry
{
    private float price;
    private int numberInStock;
    
    public Product(String name, float price, int numberInStock)
    {
        super(name);
        this.price = price;
        this.numberInStock = numberInStock;
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
}
