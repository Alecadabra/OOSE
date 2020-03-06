import java.util.*;

import javax.imageio.ImageReadParam;

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

    public insert(String fileName, String caption)
    {
        imageRecords.add(new ImageRecord(fileName, caption));
    }

    public String getFileName()
    {
        return imageRecords[i].getFileName();
    }

    public String getCaption()
    {
        return imageRecords[i].getCaption();
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
        if(i < imageRecords.length)
        {
            i++;
        }
        else
        {
            throw new IllegalArgumentException("Cannot go any futher forward");
        }
    }
}
