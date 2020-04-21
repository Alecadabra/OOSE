package model;

/** 
 * Checked exception for when the catalogue fails to read from file or 
 * otherwise validate product/group information. 
 */
public class CatalogueException extends Exception
{
    public CatalogueException(String msg)
    {
        super(msg);
    }
    
    public CatalogueException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
