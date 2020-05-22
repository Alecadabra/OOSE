package Controller;

import java.util.Arrays;
import java.util.List;

import Model.Characters.PlayerCharacter;
import View.UserInterface;

public class Controller
{
    private UserInterface ui;
    private PlayerCharacter player;
    private EnemyFactory enemyFactory;
    
    public Controller(UserInterface ui, PlayerCharacter player,
        EnemyFactory enemyFactory)
    {
        this.ui = ui;
        this.player = player;
        this.enemyFactory = enemyFactory;
    }

    public void run()
    {
        boolean exit = false;
        List<String> menuPrompts = Arrays.asList(
            "Go to Shop",
            "Choose Character Name",
            "Choose Weapon",
            "Choose Armour",
            "Start Battle",
            "Exit Game"
        );
        List<String> menuOptions = Arrays.asList(
            "shop",
            "character", "name",
            "weapon",
            "armour",
            "start", "battle",
            "exit"
        );

        // Welcome screen
        ui.showWelcome();

        while(!exit)
        {
            // Main menu
            ui.showList("Main Menu", menuPrompts);
            switch(ui.inputFrom("Select an option", menuOptions))
            {
                case "shop":
                {
                    // Go to shop
                    shop();
                    break;
                }
                case "character":
                case "name":
                {
                    // Choose character name
                    player.setName(ui.inputUnchecked("Enter name"));
                    break;
                }
                case "weapon":
                {
                    // Choose weapon
                    // TODO select weapon
                    break;
                }
                case "armour":
                {
                    // Choose armour
                    // TODO select armour
                    break;
                }
                case "start":
                case "battle":
                {
                    // Start battle
                    battle();
                    break;
                }
                case "exit":
                {
                    // Exit Game
                    exit = true;
                    ui.showGoodbye();
                    break;
                }
            }
        }
    }

    private void shop()
    {
        // TODO stub
    }

    private void battle()
    {
        // TODO stub
    }
}