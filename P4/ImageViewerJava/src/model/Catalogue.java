package model;

import java.io.*;
import java.util.*;

/**
 * Represents the overall product catalogue, including all the groups therein.
 */ 
public class Catalogue
{
    private Set<ProductGroup> groups = new TreeSet<>();
    private Map<String,ProductGroup> groupMap = new HashMap<>();
    
    public Catalogue() {}
    
    /** Retrieves all existing product groups. */
    public Set<ProductGroup> getProductGroupSet()
    {
        return Collections.unmodifiableSet(groups);
    }
    
    /** 
     * Retrieves a specific named product group, or null if no group has the 
     * name specified. 
     */
    public ProductGroup getProductGroup(String name)
    {
        return groupMap.get(name);
    }
    
    /** Adds a new product group. */
    public void addProductGroup(ProductGroup group)
    {
        if(groups.contains(group))
        {
            throw new IllegalArgumentException("ProductGroup already exists");
        }
        groups.add(group);
        groupMap.put(group.getName(), group);
    }

    /**
     * Reads a catalogue file, given a filename.
     *
     * @param filename The file storing the list of image filenames and their captions.
     * @throws CatalogueException If the file could not be read or parsed.
     */
    public void readFrom(String filename) throws CatalogueException
    {
        // Note: this is a try-with-resources statement.
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                if(line.trim().length() > 0) // Ignore blank lines
                {
                    String[] parts = line.split(":", 4);                
                    switch(parts.length)
                    {
                        case 2:
                            parseProductGroup(parts[0], parts[1]);
                            break;
                        
                        case 4:
                            parseProduct(parts[0], parts[1], parts[2], parts[3]);
                            break;
                            
                        default:
                            throw new CatalogueException(String.format(
                                "Invalid record format: '%s'", line));
                    }
                }
                            
                line = reader.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            throw new CatalogueException(String.format(
                "'%s' not found", filename));
        }
        catch(IOException e)
        {
            throw new CatalogueException(String.format(
                "IO error occurred while reading '%s': '%s'",
                filename, e.getMessage()));
        }
    }
    
    /** Adds a new product, given product information in String form. */
    private void parseProduct(String productName, String groupName, 
                              String priceStr, String numberInStockStr) throws CatalogueException
    {
        ProductGroup group = groupMap.get(groupName);
        if(group == null)
        {
            throw new CatalogueException(String.format(
                "ProductGroup '%s' not defined for product '%s'", 
                groupName, productName));
        }
        
        try
        {
            float price = Float.parseFloat(priceStr);
            int numberInStock = Integer.parseInt(numberInStockStr);
            group.addProduct(new Product(productName, price, numberInStock));
        }
        catch(NumberFormatException e)
        {
            throw new CatalogueException(String.format(
                "Product '%s' has an invalid price ('%s') and/or numberInStock ('%s')", 
                productName, priceStr, numberInStockStr));                
        }
    }
    
    /** Adds a new product group, given group information in String form. */
    private void parseProductGroup(String groupName, String parentName) throws CatalogueException
    {
        // TODO: we'll need this check, but it will fail with the current 
        // 'catalogue.txt' file, since the parent groups have not been specified.
        
        // ProductGroup parent = groupMap.get(parentName);
        // if(parent == null)
        // {
        //     throw new CatalogueException(String.format(
        //         "Parent group '%s' not defined for group '%s'",
        //         parentName, groupName));
        // }
        
        ProductGroup group = new ProductGroup(groupName);
        groups.add(group);
        groupMap.put(groupName, group);
    }
}
