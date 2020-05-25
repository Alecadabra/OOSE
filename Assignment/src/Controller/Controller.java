package Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Controller.ControllerScreens.ControllerScreen;
import Model.Characters.PlayerCharacter;
import Model.Items.ShopStorage;
import View.UserInterface;
import Controller.ControllerScreens.*;

public class Controller
{
    private UserInterface ui;
    private PlayerCharacter player;
    private HashMap<String, ControllerScreen> screens;
    
    /**
     * Constructor.
     * @param ui UserInterface to use
     * @param player PlayerCharacter that is playing
     * @param enemyFactory EnemyFactory to use when starting a battle
     * @param shopStorage ShopStorage to store shop items
     */
    public Controller(UserInterface ui, PlayerCharacter player,
        EnemyFactory enemyFactory, ShopStorage shopStorage)
    {
        this.ui = ui;
        this.player = player;
        this.screens = new HashMap<>();

        Shop shop = new Shop(ui, player, shopStorage);
        screens.put("shop", shop);

        NameSelection nameSelection = new NameSelection(ui, player);
        screens.put("character", nameSelection);
        screens.put("name", nameSelection);

        WeaponSelection weaponSelection = new WeaponSelection(ui, player);
        screens.put("weapon", weaponSelection);

        ArmourSelection armourSelection = new ArmourSelection(ui, player);
        screens.put("armour", armourSelection);

        Battle battle = new Battle(ui, player, enemyFactory);
        screens.put("start", battle);
        screens.put("battle", battle);
        screens.put("fight", battle);

        Exit exit = new Exit(ui, player);
        screens.put("exit", exit);
        screens.put("quit", exit);
    }

    public void execute()
    {
        List<String> menuPrompts = Arrays.asList(
            "Go to Shop",
            "Choose Character Name",
            "Choose Weapon",
            "Choose Armour",
            "Start Battle",
            "Exit Game"
        );

        while(player.getHp() > 0)
        {
            // Main menu
            ui.heading("Main Menu");

            ui.showList("Options", menuPrompts);
            screens.get(
                ui.inputFrom(
                    "Select an option",
                    Arrays.asList(screens.keySet().toArray())
                )
            ).run();
        }

        ui.heading("Goodbye");
    }
}