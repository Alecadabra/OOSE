package Controller.ControllerScreens;

import Controller.EnemyFactory;
import Model.Characters.PlayerCharacter;
import View.UserInterface;

public class Battle extends ControllerScreen
{
    private EnemyFactory enemyFactory;

    public Battle(UserInterface ui, PlayerCharacter player,
        EnemyFactory enemyFactory)
    {
        super("Battle", ui, player);
        this.enemyFactory = enemyFactory;
    }

    @Override
    void execute()
    {
        // TODO Stub
    }
}