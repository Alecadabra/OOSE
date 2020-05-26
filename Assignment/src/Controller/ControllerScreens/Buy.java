package Controller.ControllerScreens;

import java.util.LinkedList;
import java.util.List;

import Controller.EnchantmentHandler;
import Controller.EnchantmentHandlerException;
import Model.Characters.CharacterException;
import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import Model.Items.ItemStorage;
import Model.Items.Enchantments.Enchantable;
import Model.Items.Enchantments.Enchantment;
import View.UserInterface;

public class Buy extends ControllerScreen
{
    private ItemStorage shopStorage;
    private EnchantmentHandler enchantmentHandler;
    
    public Buy(UserInterface ui, PlayerCharacter player,
        ItemStorage shopStorage, EnchantmentHandler enchantmentHandler)
    {
        super("Item Shop", ui, player);
        this.shopStorage = shopStorage;
        this.enchantmentHandler = enchantmentHandler;
    }

    void execute()
    {
        List<Item> equippableItems = shopStorage.getAllEquippable();
        List<Item> wearableItems = shopStorage.getAllWearable();
        List<Item> usableItems = shopStorage.getAllUsable();
        List<Enchantment> enchantments = shopStorage.getAllEnchantments();
        LinkedList<String> itemsWithPrices = new LinkedList<>();
        List<String> inputOptions = shopStorage.getAllNames();
        String choice;
        Item boughtItem = null;

        for(Item curr : shopStorage.getAll())
        {
            itemsWithPrices.add(String.format(
                "%s (%d gold)", curr.getDescription(), curr.getCost()));
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");

        ui.display(String.format("Items for sale - You have %d gold",
            player.getGold()));
        ui.showList("Equippable Items", equippableItems);
        ui.showList("Wearable Items", wearableItems);
        ui.showList("Usable Items", usableItems);
        ui.showList("Enchantments", enchantments);
        choice = ui.inputFrom(
            "Enter the name of an item to purchase, or cancel",
            inputOptions);
        if(!(choice.equals("exit") || choice.equals("cancel")))
        {
            boughtItem = shopStorage.getCopy(choice);

            if(boughtItem == null)
            {
                ui.log("Couldn't get item, you have not been charged");
            }
            else if(boughtItem.getCost() > player.getGold())
            {
                ui.log("You cannot afford this item");
            }
            else if(player.getInventory().isFull())
            {
                ui.log("You do not have any remaining inventory slots");
            }
            else
            {
                try
                {
                    player.removeGold(boughtItem.getCost());
                    ui.log(String.format(
                        "Bought %s for %d gold. You now have %d gold",
                        boughtItem.getName(), boughtItem.getCost(),
                        player.getGold()));;
                    if(boughtItem instanceof Enchantment)
                    {
                        enchantItem((Enchantment)boughtItem);
                        // Well that's not very polymorphic is it
                    }
                    else
                    {
                        player.getInventory().add(boughtItem);
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
        List<Item> enchantableItems = shopStorage.getAllEnchantable();
        List<String> inputOptions = shopStorage.getAllEnchantableNames();
        String choice;
        Item item, enchantedItem;

        ui.display("Enchant Item");

        if(enchantableItems.isEmpty())
        {
            // Refund the item
            player.addGold(enchant.getCost());
            ui.log(String.format("You do not have any enchantable items, " +
                "%s automatically returned for full price",
                enchant.getName()));
        }
        else
        {
            // Add options to cancel and refund (Same action)
            inputOptions.add("cancel");
            inputOptions.add("refund");
            inputOptions.add("exit");

            ui.showList("Enchantable Items", enchantableItems);
            choice = ui.inputFrom(
                "Select item to enchant, or cancel and refund", inputOptions);

            if(choice.equals("cancel") || choice.equals("refund") ||
                choice.equals("exit"))
            {
                // Refund the item
                player.addGold(enchant.getCost());
                ui.log(String.format(
                    "%s automatically returned for full price",
                    enchant.getName()));
            }
            else
            {
                try
                {
                    // Enchant the selected item
                    item = player.getInventory().remove(choice);
                    enchantedItem = enchantmentHandler.enchant(
                        enchant.getClass().getName(), (Enchantable)item);
                    player.getInventory().add(enchantedItem);
                    if(player.isEquipped(item))
                    {
                        player.equip(enchantedItem);
                    }
                    ui.log(String.format("Enchanted %s with %s",
                        item.getName(), enchant.getName()));
                }
                catch(EnchantmentHandlerException e)
                {
                    ui.log("Could not enchant item; " +
                        e.getMessage());
                    player.addGold(enchant.getCost());
                    ui.log(String.format(
                        "%s automatically returned for full price",
                        enchant.getName(), enchant.getCost()));
                }
            }
        }
    }
}