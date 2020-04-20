/**
 * Represents an image in an album.
 */
public class ImageRecord implements Image
{
    private String filename;
    private String details;
    
    public ImageRecord(String newFilename, String newDetails)
    {
        filename = newFilename;
        details = newDetails;
    }
    
    @Override public String getFileName()
    {
        return filename;
    }
    
    @Override public String getDetails()
    {
        return details;
    }
}