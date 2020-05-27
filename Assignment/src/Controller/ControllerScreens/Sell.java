package Controller.ControllerScreens;

import java.util.List;

import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import Model.Items.ItemStorage;
import View.UserInterface;

/**
 * Screen for the user to sell an inventory item back to the item shop.
 */
public class Sell extends ControllerScreen
{
    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     */
    public Sell(UserInterface ui, PlayerCharacter player)
    {
        super("Sell Item", ui, player);
    }

    void execute()
    {
        ItemStorage inv = player.getInventory();
        List<String> itemsWithPrices = inv.getAllSells();
        List<String> inputs = inv.getAllNames();
        String choice;
        Item item;
        Item equipped = inv.get(player.getEquippedName());
        Item wearing = inv.get(player.getWearingName());

        // Remove the player's equipped and worn items from the list
        itemsWithPrices.remove(String.format("%s (Sell for %d gold)",
            equipped.getDescription(), equipped.getCost()));
        itemsWithPrices.remove(String.format("%s (Sell for %d gold)",
            wearing.getDescription(), wearing.getCost()));
        inputs.remove(player.getEquippedName());
        inputs.remove(player.getWearingName());

        if(itemsWithPrices.isEmpty())
        {
            // No items to sell
            ui.log("You do not have any items to sell");
            ui.inputUnchecked("Press Enter to continue");
        }
        else
        {
            ui.showList("Items to sell", itemsWithPrices);
            choice = ui.inputFrom(
                "Enter the name of an item to sell, or cancel.",
                inputs);

            // Make sure the player didn't choose to exit
            if(choice != null)
            {
                item = inv.remove(choice);
                player.addGold(item.getSell());
                ui.log(String.format(
                    "Sold %s for %d gold. You now have %d gold",
                    item.getName(), item.getSell(), player.getGold()));

                ui.inputUnchecked("Press Enter to continue");
            }
        }
    }
}