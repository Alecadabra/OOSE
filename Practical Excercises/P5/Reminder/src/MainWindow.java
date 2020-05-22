import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

/**
 * The main reminder application window. This contains a list of the reminders,
 * along with an "add" button that opens an "add reminder" window, and a 
 * "remove" button that removes the currently-selected reminder.
 */
public class MainWindow extends JFrame
{
    private JList<String> remindersWidget;
    private JButton addButton;
    private JButton removeButton;
    
    private AddReminderWindow addReminderWindow;
    private Controller controller;
    
    /**
     * Set everything up. We need the controller for two purposes: (1) so we 
     * can tell it to remove a reminder, and (2) so we can pass it on to the 
     * AddReminderWindow constructor.
     */
    public MainWindow(Controller inController)
    {
        super("Reminder App");                          // Window title.
        setPreferredSize(new Dimension(400, 150));      // Window size
        
        // Make the whole program close when this window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
        controller = inController;
        addReminderWindow = new AddReminderWindow(controller);
        
        // Our important widgets:
        remindersWidget = new JList<String>();
        remindersWidget.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        // Boring window layout stuff.
        JPanel contentPane = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(remindersWidget);
        JToolBar toolbar = new JToolBar();
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        toolbar.add(addButton);
        toolbar.add(removeButton);
        getRootPane().setContentPane(contentPane);
        
        
        // When the "add" button is clicked, make the "add reminder" window visible.
        addButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    addReminderWindow.setVisible(true);
                }
            }
        );
        
        // When the "remove" button is clicked, tell the controller to remove
        // the currently selected reminder.
        removeButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Figure out which reminder is currently selected.
                    int index = remindersWidget.getSelectedIndex();
                    if(index != -1)
                    {
                        // If there *is* something currently selected, tell the
                        // controller to remove it.
                        controller.removeReminder(index);
                    }
                }
            }
        );
        
        showReminders(controller.getReminders());
        
        // Trigger the layout algorithm.
        pack();
    }
    
    /**
     * Takes a list of Reminder objects and displays them in the window.
     */
    public void showReminders(List<Reminder> reminders)
    {
        Vector<String> tasks = new Vector<String>();
        for(Reminder rem : reminders)
        {
            tasks.add(rem.getTask());
        }
        remindersWidget.setListData(tasks);
    }
}
