import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;


/**
 * The window for adding reminders. Contains a text field, plus "Add" and "Close" buttons.
 */
public class AddReminderWindow extends JFrame
{
    private static final int PADDING = 10;
    
    private Controller controller;
    private JTextField taskWidget;
    private JButton addButton;
    private JButton closeButton;
    
    /** 
     * Set everything up. We need to import a Controller reference, because 
     * this is where we tell the controller to add a reminder. 
     */
    public AddReminderWindow(Controller inController)
    {
        super("Add Reminder"); // Window title.
        
        controller = inController;
        
        // These are the important widgets.
        taskWidget = new JTextField(50);    // Space to enter some reminder text. 
        addButton = new JButton("Add");     // A button to add the reminder.
        closeButton = new JButton("Close"); // A button to close the window.
        
        
        // Now we do the boring window layout stuff.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(PADDING, 0)));
        buttonPanel.add(closeButton);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        contentPane.add(taskWidget);
        contentPane.add(Box.createRigidArea(new Dimension(0, PADDING)));
        contentPane.add(buttonPanel);
        getRootPane().setContentPane(contentPane);
        
        
        // The add button tells the controller to add a new reminder.
        addButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Retrieve the text entered by the user.
                    String task = taskWidget.getText();
                    if(task.length() > 0)
                    {
                        // *If* there is some text present, add a new reminder.
                        controller.addReminder(task, new Date(0l));
                    }
                }
            }
        );
        
        // The close button simply hides this window; i.e. making it invisible 
        // to the user (but still present in memory).
        closeButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                }
            }
        );
        
        // Trigger the window layout algorithm.
        pack();
    }
}
