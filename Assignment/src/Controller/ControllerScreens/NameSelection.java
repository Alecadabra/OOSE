package Controller.ControllerScreens;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

public class NameSelection extends ControllerScreen
{
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
