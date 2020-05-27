package Controller.ControllerScreens;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

/**
 * Screen for the player to choose their display name.
 */
public class NameSelection extends ControllerScreen
{
    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     */
    public NameSelection(UserInterface ui, PlayerCharacter player)
    {
        super("Name Selection", ui, player);
    }

    @Override
    void execute()
    {
        player.setName(ui.inputUnchecked("Enter name"));
    }
}
