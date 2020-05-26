package Controller.ControllerScreens;

import java.util.ArrayList;
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

public class Buy extends ControllerScreen
{
    private ShopStorage shopStorage;
    
    public Buy(UserInterface ui, PlayerCharacter player,
        ShopStorage shopStorage)
    {
        super("Item Shop", ui, player);
        this.shopStorage = shopStorage;
    }

    void execute()
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
        inputOptions.add("q");

        ui.showList(String.format(
            "Items for sale - You have %d gold", player.getGold()),
            itemsWithPrices);
        choice = ui.inputFrom(
            "Enter the name of an item to purchase, or cancel",
            inputOptions);
        if(!(choice.equals("exit") || choice.equals("cancel") ||
            choice.equals("q")))
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
                    ui.log(String.format(
                        "Bought %s for %d gold. You now have %d gold",
                        item.getName(), item.getCost(), player.getGold()));
                    item = item.clone();
                    if(item instanceof Enchantment && !item.isEnchantable())
                    {
                        enchantItem((Enchantment)item);
                        // Well that's not very polymorphic is it
                    }
                    else
                    {
                        player.addItem(item);
                    }
                }
                catch(CharacterException e)
                {
                    ui.log("Couldn't purchase item; " + e.getMessage());
                }
            }
            ui.inputUnchecked("Press Enter to continue");
        }
    }

    private void enchantItem(Enchantment enchant)
    {
        // All of the player's items
        List<Item> allItems = player.getAllItems();
        // All of the player's enchantable items
        ArrayList<String> enchantableItems = new ArrayList<>();
        // All of the options for the user to enter
        ArrayList<String> inputOptions = new ArrayList<>();
        // User's choice
        String choice;
        // Item being enchanted
        Item item, enchantedItem;
        // EnchantmentHandler from the shop to use to enchant the item
        EnchantmentHandler enchanter = shopStorage.getEnchantmentHandler();

        ui.display("Enchant Item");

        for(Item invItem : allItems)
        {
            if(invItem.isEnchantable())
            {
                if(invItem.getName().contains("["))
                {
                    // Enchanted item - add tip on how to select
                    enchantableItems.add(invItem.getDescription() +
                        " *Enter '" + invItem.getName() + "'*");
                }
                else
                {
                    enchantableItems.add(invItem.getDescription());
                }
                
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
            inputOptions.add("cancel");
            inputOptions.add("refund");
            inputOptions.add("exit");
            inputOptions.add("q");

            ui.showList("Enchantable Items", enchantableItems);
            choice = ui.inputFrom(
                "Select item to enchant, or cancel and refund", inputOptions);

            if(choice.equals("cancel") || choice.equals("refund") ||
                choice.equals("exit") || choice.equals("q"))
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
                    item = player.removeItem(choice);
                    enchantedItem = enchanter.enchant(
                        enchant.getClass().getName(), (Enchantable)item);
                    player.addItem(enchantedItem);
                    if(player.isEquipped(item.getName()))
                    {
                        player.equip(enchantedItem.getName());
                    }
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