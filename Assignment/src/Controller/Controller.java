package Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Controller.ControllerScreens.ControllerScreen;
import Model.Characters.PlayerCharacter;
import Model.Items.DamagePotion;
import Model.Items.ItemException;
import Model.Items.ItemStorage;
import View.UserInterface;
import Controller.ControllerScreens.*;

/**
 * Controls the main menu and calls controller screens based on the user's
 * menu selection.
 */
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
     * @param shopStorage ItemStorage populated with shop items
     * @param enchantmentHandler EnchantmentHandler populated with enchantments
     */
    public Controller(UserInterface ui, PlayerCharacter player,
        EnemyFactory enemyFactory, ItemStorage shopStorage,
        EnchantmentHandler enchantmentHandler)
    {
        this.ui = ui;
        this.player = player;
        this.screens = new HashMap<>();

        Buy buy = new Buy(ui, player, shopStorage, enchantmentHandler);
        screens.put("buy", buy);
        screens.put("shop", buy);

        Sell sell = new Sell(ui, player);
        screens.put("sell", sell);

        NameSelection nameSelection = new NameSelection(ui, player);
        screens.put("character", nameSelection);
        screens.put("name", nameSelection);

        WeaponSelection weaponSelection = new WeaponSelection(ui, player);
        screens.put("weapon", weaponSelection);
        screens.put("equip", weaponSelection);

        ArmourSelection armourSelection = new ArmourSelection(ui, player);
        screens.put("armour", armourSelection);
        screens.put("wear", armourSelection);

        Battle battle = new Battle(ui, player, enemyFactory);
        screens.put("start", battle);
        screens.put("battle", battle);
        screens.put("fight", battle);
    }

    /**
     * Runs the main menu until the player exits, dies or defeats the boss.
     * On each return to the menu, this method simulates
     * ControllerScreen.run() by calling ui.clear, shows the player's
     * attributes and inventory, the heading 'Main Menu', and then runs the
     * menu.
     */
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
        List<String> inputs = Arrays.asList(
            "buy", "shop",
            "sell",
            "character", "name",
            "weapon", "equip",
            "armour", "wear",
            "start", "battle", "fight",
            "exit",
            "vegemite" // Cheat code
        );
        ControllerScreen selection;
        String input;

        while(player.getHp() > 0)
        {
            // Main menu
            ui.clear();

            ui.showList("Player Attributes", player.getAttributes());

            ui.showList(String.format(
                "Inventory (%d/%d)", player.getInventory().getCount(), 
                player.getInventory().getCapacity()),
                player.getInventory().getAllNames());

            ui.heading("Main Menu");

            ui.showList("Menu Options", menuPrompts);
            input = ui.inputFrom("Select an option, eg. \"shop\"", inputs);
            selection = screens.get(input);

            if(input.equals("vegemite"))
            {
                // Cheat code because the dragon is kinda impossible :)
                try
                {
                    player.getInventory().add(new DamagePotion(
                        "Vegemite Scroll", 100, 120, 150));
                }
                catch(ItemException e) {}
            }
            else if(selection != null)
            {
                selection.run();
            }
            else
            {
                // Player has chosen to exit
                player.kill();
                ui.heading("Goodbye!");
            }
        }
    }
}