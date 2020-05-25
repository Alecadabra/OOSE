package Controller.ControllerScreens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.EnchantmentHandler;
import Controller.EnchantmentHandlerException;
import Model.Characters.CharacterException;
import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import Model.Items.ShopStorage;
import Model.Items.Enchantments.Enchantable;
import Model.Items.Enchantments.Enchantment;
import View.UserInterface;

public class Shop extends ControllerScreen
{
    private ShopStorage shopStorage;

    public Shop(UserInterface ui, PlayerCharacter player,
        ShopStorage shopStorage)
    {
        super("Shop", ui, player);
        this.shopStorage = shopStorage;
    }

    @Override
    void execute()
    {
        boolean exit = false;
        List<String> inputOptions = Arrays.asList(
            "purchase", "buy", "shop",
            "sell",
            "exit", "cancel"
        );
        String choice;

        while(!exit)
        {
            ui.showList("Items for sale", shopStorage.getItems());
            choice = ui.inputFrom(
                "You can shop for items to buy, sell items or exit",
                inputOptions);

            switch(choice)
            {
                case "purchase":
                case "buy":
                case "shop":
                {
                    purchaseItem();
                    break;
                }
                case "sell":
                {
                    sellItem();
                    break;
                }
                case "exit":
                case "cancel":
                {
                    exit = true;
                    break;
                }
            }
        }
    }

    private void purchaseItem()
    {
        List<Item> shopItems = shopStorage.getItems();
        ArrayList<String> itemsWithPrices = new ArrayList<>();
        ArrayList<String> inputOptions = new ArrayList<>();
        String choice;
        Item item = null;

        for(Item curr : shopItems)
        {
            itemsWithPrices.add(String.format(
                "%s (%d gold)", curr.getDescription(), curr.getCost()));
            inputOptions.add(curr.getName());
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");

        ui.showList("Items for sale", itemsWithPrices);
        choice = ui.inputFrom(
            "Enter the name of an item to purchase, or cancel",
            inputOptions);
        if(!(choice.equals("exit") || choice.equals("cancel")))
        {
            item = shopStorage.getItem(choice);

            if(item == null)
            {
                ui.log("Couldn't get item, you have not been charged");
            }
            else if(item.getCost() > player.getGold())
            {
                ui.log("You cannot afford this item");
            }
            else if(player.getFreeSlots() == 0)
            {
                ui.log("You do not have any remaining inventory slots");
            }
            else
            {
                try
                {
                    player.removeGold(item.getCost());
                    if(item instanceof Enchantment)
                    {
                        enchantItem((Enchantment)item);
                        // Well that's not very polymorphic is it
                    }
                    else
                    {
                        player.addItem(item);
                        ui.log(String.format(
                            "Bought %s for %d gold. You now have %d gold",
                            item.getName(), item.getCost(), player.getGold()));
                    }
                }
                catch(CharacterException e)
                {
                    ui.log("Couldn't purchase item; " + e.getMessage());
                }
            }
        }
    }

    private void sellItem()
    {
        List<Item> inv = player.getAllItems();
        ArrayList<String> itemsWithPrices = new ArrayList<>();
        ArrayList<String> inputOptions = new ArrayList<>();
        String choice;
        Item item;

        for(Item curr : inv)
        {
            itemsWithPrices.add(String.format("%s (%d gold sell price)",
                curr.getDescription(), curr.getSell()));
            inputOptions.add(curr.getName());
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");

        ui.showList("Items to sell", itemsWithPrices);
        choice = ui.inputFrom(
            "Enter the name of an item to sell, or cancel.",
            inputOptions);

            if(!(choice.equals("exit") || choice.equals("cancel")))
            {
                try
                {
                    item = player.removeItem(choice);
                    player.addGold(item.getSell());
                    ui.log(String.format(
                        "Sold %s for %d gold. You now have %d gold",
                        item.getName(), item.getSell(), player.getGold()));
                }
                catch(CharacterException e)
                {
                    ui.log("Couldn't get item, you have not been charged");
                }
            }
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

        ui.display("Enchant item");

        for(Item invItem : allItems)
        {
            if(invItem.isEnchantable())
            {
                enchantableItems.add(invItem);
                inputOptions.add(invItem.getName());
            }
        }

        if(enchantableItems.isEmpty())
        {
            // Refund the item
            player.addGold(enchant.getSell());
            ui.log(String.format("You do not have any enchantable items, " +
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
                ui.log(String.format("%s returned to shop for %d gold",
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
                    ui.log(String.format(
                        "%s returned to shop for %d gold",
                        enchant.getName(), enchant.getSell()));
                }
            }
        }
    }
}