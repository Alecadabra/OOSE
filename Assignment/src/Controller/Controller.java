package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Characters.CharacterException;
import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import Model.Items.ShopStorage;
import Model.Items.Enchantments.Enchantable;
import Model.Items.Enchantments.Enchantment;
import View.UserInterface;

public class Controller // TODO rename
{
    private UserInterface ui;
    private PlayerCharacter player;
    private EnemyFactory enemyFactory;
    private ShopStorage shopStorage;
    
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
        this.enemyFactory = enemyFactory;
        this.shopStorage = shopStorage;
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
        ui.heading("Welcome");

        while(!exit)
        {
            // Main menu
            ui.heading("Main Menu");
            ui.showList("Options", menuPrompts);
            switch(ui.inputFrom("Select an option", menuOptions))
            {
                case "shop":
                {
                    // Go to shop
                    ui.heading("Shop");
                    shop();
                    break;
                }
                case "character":
                case "name":
                {
                    // Choose character name
                    ui.heading("Name Selection");
                    player.setName(ui.inputUnchecked("Enter name"));
                    break;
                }
                case "weapon":
                {
                    // Choose weapon
                    ui.heading("Equip Weapon");
                    // TODO select weapon
                    break;
                }
                case "armour":
                {
                    // Choose armour
                    ui.heading("Equip Armour");
                    // TODO select armour
                    break;
                }
                case "start":
                case "battle":
                {
                    // Start battle
                    ui.heading("Battle");
                    battle();
                    break;
                }
                case "exit":
                {
                    // Exit Game
                    exit = true;
                    ui.heading("Goodbye");
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

    private void enchantItem(Enchantment enchant)
    {
        // All of the player's items
        List<Item> allItems = player.getAllItems();
        // All of the player's enchantable items
        ArrayList<Item> enchantableItems = new ArrayList<>();
        // All of the options for the user to enter
        ArrayList<String> inputOptions = new ArrayList<>();
        // User's choice
        String choice;
        // Item being enchanted
        Enchantable item;
        // EnchantmentHandler from the shop to use to enchant the item
        EnchantmentHandler enchanter = shopStorage.getEnchantmentHandler();

        ui.heading("Enchant item");

        for(Item invItem : allItems)
        {
            if(invItem.isEnchantable())
            {
                enchantableItems.add(invItem);
                inputOptions.add(invItem.getDescription());
            }
        }

        if(enchantableItems.isEmpty())
        {
            // Refund the item
            player.addGold(enchant.getSell());
            ui.display(String.format("You do not have any enchantable items, " +
            "%s returned to shop for %d gold",
                enchant.getName(), enchant.getSell()));
        }
        else
        {
            // Select item and enchant

            // Add options to cancel and refund (Same action)
            inputOptions.add("Cancel");
            inputOptions.add("Refund");

            ui.showList("Enchantable Items", enchantableItems);
            choice = ui.inputFrom(
                "Select item to enchant, or cancel and refund", inputOptions);

            if(choice.equals("Cancel") || choice.equals("Refund"))
            {
                // Refund the item
                player.addGold(enchant.getSell());
                ui.display(String.format("%s returned to shop for %d gold",
                    enchant.getName(), enchant.getSell()));
            }
            else
            {
                try
                {
                    // Enchant the selected item
                    item = (Enchantable)player.removeItem(choice);
                    item = enchanter.enchant(
                        enchant.getClass().getName(), item);
                    player.addItem(item);
                    ui.log(String.format("Enchanted %s with %s",
                        item.getName(), enchant.getName()));
                }
                catch(CharacterException | EnchantmentHandlerException e)
                {
                    ui.log("Could not enchant item; " +
                        e.getMessage());
                    player.addGold(enchant.getSell());
                    ui.display(String.format(
                        "%s returned to shop for %d gold",
                        enchant.getName(), enchant.getSell()));
                }
            }
        }
    }
}