package Controller.ControllerScreens;

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

/**
 * Item shop screen for purchasing an item. Stores an ItemStorage with all the
 * shop's items and an enchantment handler to use to enchant items if the
 * player buys an enchantment.
 */
public class Buy extends ControllerScreen
{
    private ItemStorage shopStorage;
    private EnchantmentHandler enchantmentHandler;
    
    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     * @param shopStorage ItemStorage populated with all the shop's items
     * @param enchantmentHandler Initialised enchantment handler
     */
    public Buy(UserInterface ui, PlayerCharacter player,
        ItemStorage shopStorage, EnchantmentHandler enchantmentHandler)
    {
        super("Item Shop", ui, player);
        this.shopStorage = shopStorage;
        this.enchantmentHandler = enchantmentHandler;
    }

    void execute()
    {
        ItemStorage inv = player.getInventory();
        List<String> equippableItems = shopStorage.getAllEquippableCosts();
        List<String> wearableItems = shopStorage.getAllWearableCosts();
        List<String> usableItems = shopStorage.getAllUsableCosts();
        List<String> enchantments = shopStorage.getAllEnchantmentsCosts();
        List<String> inputs = shopStorage.getAllNames();
        String choice;
        Item boughtItem = null;

        // Remove items that cant be afforded - uses a lambda!
        inputs.removeIf(p -> shopStorage.get(p).getCost() > player.getGold());

        // Show options
        ui.show(String.format("Items for sale - You have %d gold",
            player.getGold()));
        ui.showList("Equippable Items", equippableItems);
        ui.showList("Wearable Items", wearableItems);
        ui.showList("Usable Items", usableItems);
        ui.showList("Enchantments", enchantments);
        choice = ui.inputFrom(
            "Enter the name of an item to purchase, or cancel", inputs);

        // Make sure the user didn't choose to cancel
        if(choice != null)
        {
            boughtItem = shopStorage.getCopy(choice);
                // Get a copy so that nothing weird happens down the line

            if(boughtItem == null)
            {
                // ShopStorage.getCopy couldn't get the item (Shouldn't happen)
                ui.log("Couldn't get item, you have not been charged");
            }
            else if(boughtItem.getCost() > player.getGold())
            {
                ui.log("You cannot afford this item");
            }
            else if(inv.isFull())
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
                        player.getGold()));
                    
                    // Check if we just bought an enchantment
                    if(boughtItem instanceof Enchantment)
                    {
                        enchantItem((Enchantment)boughtItem);
                        // Well that's not very polymorphic is it
                    }
                    else
                    {
                        inv.add(boughtItem);
                    }
                }
                catch(CharacterException e)
                {
                    // Shouldn't ever happen
                    ui.log("Couldn't purchase item; " + e.getMessage());
                }
            }
            ui.inputUnchecked("Press Enter to continue");
        }
    }

    /**
     * Applies a given enchantment to an inventory item of the player's choice.
     * @param enchant The enchantment to apply
     */
    private void enchantItem(Enchantment enchant)
    {
        ItemStorage inv = player.getInventory();
        List<Item> enchantableItems = inv.getAllEnchantable();
        List<String> inputs = inv.getAllEnchantableNames();
        String choice;
        Item item, enchantedItem;

        ui.show("Enchant Item");

        if(enchantableItems.isEmpty())
        {
            // Player has nothing enchantable - refund the item
            player.addGold(enchant.getCost());
            ui.log(String.format("You do not have any enchantable items, " +
                "%s automatically returned for full price",
                enchant.getName()));
        }
        else
        {
            ui.showList("Enchantable Items", enchantableItems);
            choice = ui.inputFrom(
                "Select item to enchant, or cancel", inputs);

            if(choice == null)
            {
                // Player chose to canel - refund the item
                player.addGold(enchant.getCost());
                ui.log(String.format(
                    "%s automatically returned for full price",
                    enchant.getName()));
            }
            else
            {
                // Enchant the selected item
                try
                {
                    // Remove the enchantable from inventory
                    item = inv.remove(choice);

                    // Use enchantment handler to apply enchantment
                    enchantedItem = enchantmentHandler.enchant(
                        enchant.getClass().getName(), (Enchantable)item);

                    // Add the now enchanted item back into the inventory
                    inv.add(enchantedItem);

                    // If we just enchanted an equipped item, update it
                    if(player.isEquipped(item))
                    {
                        player.equip(enchantedItem);
                    }
                    if(player.isWearing(item))
                    {
                        player.wear(item);
                    }
                    ui.log(String.format("Enchanted %s with %s",
                        item.getName(), enchant.getName()));
                }
                catch(EnchantmentHandlerException e)
                {
                    // Shouldn't ever happen
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