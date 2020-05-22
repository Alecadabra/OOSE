import java.util.Date;

/**
 * Represents a Reminder, having a piece of text describing the task, and a
 * date/time on which to be reminded about it.
 */
public class Reminder
{
    private String task;
    private Date dateObj;
    
    public Reminder(String task, Date dateObj)
    {
        this.task = task;
        this.dateObj = dateObj;
    }
    
    // Basic accessors
    public String getTask()  { return task; }
    public Date getDateObj() { return (Date)dateObj.clone(); }
    public long getTime()    { return dateObj.getTime(); }
    
    
    // Basic mutators
    public void setTask(String task) 
    {
        this.task = task;
    }
    
    public void setDateObj(Date dateObj)
    {
        this.dateObj = dateObj;
    }
}
