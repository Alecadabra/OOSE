package Controller.ControllerScreens;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

public class Exit extends ControllerScreen
{
    public Exit(UserInterface ui, PlayerCharacter player)
    {
        super("Goodbye!", ui, player);
    }

    @Override
    void execute()
    {
        player.kill();
    }
}