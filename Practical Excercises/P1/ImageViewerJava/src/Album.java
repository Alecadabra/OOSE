import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    private ArrayList<ImageRecord> imageRecords;
    private int i;

    public Album()
    {
        imageRecords = new ArrayList<>();
        i = 0;
    }

    public void insert(String fileName, String caption)
    {
        imageRecords.add(new ImageRecord(fileName, caption));
    }

    public String getFileName()
    {
        return imageRecords.get(i).getFileName();
    }

    public String getCaption()
    {
        return imageRecords.get(i).getCaption();
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
        if(i < imageRecords.size())
        {
            i++;
        }
        else
        {
            throw new IllegalArgumentException("Cannot go any futher forward");
        }
    }
}
