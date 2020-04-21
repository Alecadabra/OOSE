package controller;
import view.MainWindow;
import model.*;

/**
 * Controller for the product viewer's (relatively limited) set of features;
 * specifically, adding product groups, adding products, and calculating the 
 * total value of products within a given group.
 */
public class Controller
{
    private Catalogue catalogue;

    public Controller(Catalogue catalogue)
    {
        this.catalogue = catalogue;
    }
   
    /**
     * Validates and adds a new product group.
     */
    public void addProductGroup(String groupName, String parentName) 
        throws ControllerException
    {
        if(groupName == null || groupName.equals(""))
        {
            throw new ControllerException(
                "The product group name cannot be null or empty.");
        }
        
        if(catalogue.getProductGroup(groupName) != null)
        {
            throw new ControllerException(String.format(
                "There's already an existing product group named '%s'.", 
                groupName));
        }
        
        catalogue.addProductGroup(new ProductGroup(groupName));
    }
    
    /** 
     * Validates and adds a new product. 
     */
    public void addProduct(String productName, String groupName, 
                           float price, int numberInStock) 
                           throws ControllerException
    {
        if(productName == null || productName.equals(""))
        {
            throw new ControllerException("The product name cannot be null or empty.");
        }
        
        if(groupName == null || groupName.equals(""))
        {
            throw new ControllerException("The product group name cannot be null or empty.");
        }
        
        if(price < 0.0f || numberInStock < 0)
        {
            throw new ControllerException("The price and number-in-stock cannot be less than 0.");
        }
        
        ProductGroup group = catalogue.getProductGroup(groupName);
        if(group == null)
        {
            throw new ControllerException(String.format(
                "Product group '%s' does not exist.", groupName));
        }
        
        Product product = new Product(productName, price, numberInStock); 
        if(group.hasProduct(product))
        {   
            throw new ControllerException(String.format(
                "Product group '%s' already has an existing product named '%s'.",
                groupName, productName));            
        }
        
        group.addProduct(product);
    }
    
    /**
     * Calculates the total value of all the products within a given product 
     * group. The calculation is done by taking 'price * number-in-stock' for
     * each product, and summing them.
     */
    public float calcValue(String groupName)
    {
        // TODO: you can arrange to have this calculation performed by the 
        // composite structure.
        
        float value = 0.0f;
        ProductGroup group = catalogue.getProductGroup(groupName);
        for(Product p : group.getProducts())
        {
            value += p.getPrice() * (float)p.getNumberInStock();
        }
        return value;
    }    
}
