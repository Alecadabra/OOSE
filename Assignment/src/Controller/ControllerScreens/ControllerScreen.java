package Controller.ControllerScreens;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

/**
 * A screen for the UserInterface to show, there is a screen for each main menu
 * option, such as the item shop and the battle. A screen has a name and stores
 * the UserInterface it uses and the PlayerCharacter that interacts with it.
 * Call run() on a screen to have it run, and implementing classes should
 * override execute() to add their functionality.
 */
public abstract class ControllerScreen
{
    protected String name;
    protected UserInterface ui;
    protected PlayerCharacter player;

    /**
     * Constructor.
     * @param name The name of this Screen
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     */
    public ControllerScreen(String name, UserInterface ui,
        PlayerCharacter player)
    {
        this.name = name;
        this.ui = ui;
        this.player = player;
    }

    /**
     * Run the screen. Calls ui.clear, shows the player's attributes and
     * inventory, the heading of the name of the screen, and calls execute().
     */
    public void run()
    {
        ui.clear();

        ui.showList("Player Attributes", player.getAttributes());

        ui.showList(String.format(
            "Inventory (%d/%d)", player.getInventory().getCount(), 
            player.getInventory().getCapacity()),
            player.getInventory().getAllNames());

        ui.heading(name);

        execute();
    }

    abstract void execute();
}