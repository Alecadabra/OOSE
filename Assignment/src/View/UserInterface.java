package View;

import java.util.List;

/**
 * A user interface providing basic input and output methods.
 */
public interface UserInterface
{
    /**
     * Clears the interface in some way. Called before each controller screen
     * is executed
     */
    public void clear();

    /**
     * Shows the heading of a controller screen
     * @param heading Heading string
     */
    public void heading(String heading);
    
    /**
     * Shows some important information text
     * @param content Content string
     */
    public void show(String content);

    /**
     * Shows a title (Using show()) and then a list
     * @param title The title of the list
     * @param list The list content, toString() will be called on each element
     */
    public void showList(String title, List<?> list);

    /**
     * Shows some basic information text
     * @param message Log string
     */
    public void log(String message);

    /**
     * Gets user input after a prompt message from a list of possible options.
     * The user can cancel and this will return null.
     * @param prompt The prompt string to show
     * @param options The list of legal options
     * @return The string chosen from options, or null if cancelled
     */
    public String inputFrom(String prompt, List<String> options);

    /**
     * Gets user input after a prompt message.
     * @param prompt The prompt string to show
     * @return The string given by the user
     */
    public String inputUnchecked(String prompt);
}