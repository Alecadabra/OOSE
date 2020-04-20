import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    private ArrayList<ImageRecord> images;
    private int i;

    public Album()
    {
        images = new ArrayList<>();
        i = 0;
    }

    public void insert(Image inImage)
    {
        images.add(inImage);
    }

    public String getFileName()
    {
        return images.get(i).getFileName();
    }

    public String getInfo()
    {
        return images.get(i).getDetails();
    }

    public void prev()
    {
        if(i > 0)
        {
            i--;
        }
        else
        {
            throw new IllegalArgumentException("Cannot go any further back");
        }
    }

    public void next()
    {
        if(i < images.size())
        {
            i++;
        }
        else
        {
            throw new IllegalArgumentException("Cannot go any futher forward");
        }
    }
}
