package Controller.ControllerScreens;

import java.util.List;

import Model.Characters.PlayerCharacter;
import Model.Items.ItemStorage;
import View.UserInterface;

/**
 * Weapon selection screen.
 */
public class WeaponSelection extends ControllerScreen
{
    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     */
    public WeaponSelection(UserInterface ui, PlayerCharacter player)
    {
        super("Weapon Selection", ui, player);
    }

    @Override
    void execute()
    {
        ItemStorage inv = player.getInventory();
        List<String> equippableItems = inv.getAllEquippableDescriptions();
        List<String> inputs = inv.getAllEquippableNames();
        String choice;

        ui.showList("Equippable Items", equippableItems);
        choice = ui.inputFrom("Select item to equip, or cancel.", inputs);

        if(choice != null)
        {
            player.equip(inv.get(choice));
            ui.log("Equipped " + choice);

            ui.inputUnchecked("Press Enter to continue");
        }
    }
}