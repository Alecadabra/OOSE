import java.io.*;
import java.util.*;

/**
 * Static methods for reading and writing the reminders file.
 */
public class FileManager
{
    /**
     * Reads a reminders file, given a filename, and returns a list of Reminder
     * objects. This reads line-by-line, and (simplistically) ignores any line
     * that doesn't conform to the expected format.
     */
    public static List<Reminder> read(String filename) throws IOException
    {
        List<Reminder> reminders = new LinkedList<Reminder>();
        BufferedReader reader = null;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(filename));
            line = reader.readLine();
            while(line != null)
            {
                Scanner sc = new Scanner(line);
                if(sc.hasNextLong())
                {
                    Date date = new Date(sc.nextLong()); 
                    String task = sc.next(".*"); // Rest of the string
                    reminders.add(new Reminder(task, date));
                }
                line = reader.readLine();
            }
        }
        finally     
        {
            if(reader != null)
            {
                reader.close();
            }
        }
        return reminders;
    }
    
    /**
     * Writes the reminders file, overwriting any existing one, given a
     * filename and a list of Reminders. This method obviously endeavours to 
     * write in the same format that the read() method would expect.
     */
    public static void write(String filename, List<Reminder> reminders) throws IOException
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(filename);
            for(Reminder rem : reminders)
            {
                writer.printf("%d %s\n", rem.getTime(), rem.getTask());
            }
        }
        finally
        {
            if(writer != null)
            {
                writer.close();
            }
        }
    }
}
