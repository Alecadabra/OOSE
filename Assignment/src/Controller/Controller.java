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

        Buy buy = new Buy(ui, player, shopStorage);
        screens.put("buy", buy);
        screens.put("shop", buy);

        Sell sell = new Sell(ui, player);
        screens.put("sell", sell);

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
        screens.put("q", exit);
    }

    public void execute()
    {
        List<String> menuPrompts = Arrays.asList(
            "Buy From the Item Shop",
            "Sell Items to the Item Shop",
            "Choose Character Name",
            "Choose Weapon",
            "Choose Armour",
            "Start Battle",
            "Exit Game"
        );

        while(player.getHp() > 0)
        {
            // Main menu
            ui.clear();

            ui.showList("Player Attributes", player.getAttributes());

            ui.showList(String.format("Inventory (%d/%d)",
                player.getUsedSlots(), player.getCapacity()),
                player.getAllItems());

            ui.heading("Main Menu");

            ui.showList("Menu Options", menuPrompts);
            screens.get(
                ui.inputFrom(
                    "Select an option, eg. \"shop\"",
                    Arrays.asList(screens.keySet().toArray())
                )
            ).run();
        }
    }
}