package Controller.ControllerScreens;

import java.util.ArrayList;
import java.util.List;

import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import View.UserInterface;

public class ArmourSelection extends ControllerScreen
{
    public ArmourSelection(UserInterface ui, PlayerCharacter player)
    {
        super("Armour Selection", ui, player);
    }

    @Override
    void execute()
    {
        List<Item> invItems = player.getAllItems();
        ArrayList<Item> wearableItems = new ArrayList<>();
        String choice;

        for(Item curr : invItems)
        {
            if(curr.isWearable())
            {
                wearableItems.add(curr);
            }
        }

        ui.showList("Wearable Items", wearableItems);
        choice = ui.inputFrom(
            "Select item to wear (Exact name)", wearableItems);
        player.wear(choice);
        ui.log("Equipped " + choice);
    }
}