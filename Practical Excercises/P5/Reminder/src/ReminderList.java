import java.util.*;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList
{
    private List<Reminder> reminders;

    public ReminderList()
    {
        reminders = new ArrayList<Reminder>();
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
    }
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }
}
