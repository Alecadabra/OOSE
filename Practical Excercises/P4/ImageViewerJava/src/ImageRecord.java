/**
 * Represents an image in an album.
 */
public class ImageRecord implements ImageFile
{
    private String filename;
    private String details;
    
    public ImageRecord(String fileName, String details)
    {
        this.filename = fileName;
        this.details = details;
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