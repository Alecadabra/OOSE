package Controller.ControllerScreens;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

public abstract class ControllerScreen
{
    protected String name;
    protected UserInterface ui;
    protected PlayerCharacter player;

    public ControllerScreen(String name, UserInterface ui,
        PlayerCharacter player)
    {
        this.name = name;
        this.ui = ui;
        this.player = player;
    }

    public void run()
    {
        ui.heading(name);

        execute();
    }

    abstract void execute();
    
    public String getName()
    {
        return name;
    }
}