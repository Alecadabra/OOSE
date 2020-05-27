package Controller.ControllerScreens;

import java.util.List;

import Model.Characters.PlayerCharacter;
import Model.Items.ItemStorage;
import View.UserInterface;

/**
 * Armour selection screen.
 */
public class ArmourSelection extends ControllerScreen
{
    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     */
    public ArmourSelection(UserInterface ui, PlayerCharacter player)
    {
        super("Armour Selection", ui, player);
    }

    @Override
    void execute()
    {
        ItemStorage inv = player.getInventory();
        List<String> wearableItems = inv.getAllWearableDescriptions();
        List<String> inputs = inv.getAllWearableNames();
        String choice;

        ui.showList("Wearable Items", wearableItems);
        choice = ui.inputFrom("Select item to wear, or cancel", inputs);

        // Make sure the user didn't choose to exit
        if(choice != null)
        {
            player.wear(inv.get(choice));
            ui.log("Equipped " + choice);
            
            ui.inputUnchecked("Press Enter to continue");
        }
    }
}