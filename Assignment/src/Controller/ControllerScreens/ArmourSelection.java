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
        ArrayList<String> wearableItems = new ArrayList<>();
        ArrayList<String> inputOptions = new ArrayList<>();
        String choice;

        for(Item curr : invItems)
        {
            if(curr.isWearable())
            {
                if(curr.getName().contains("["))
                {
                    // Enchanted item - add tip on how to select
                    wearableItems.add(curr.getDescription() +
                        " *Enter '" + curr.getName() + "'*");
                }
                else
                {
                    wearableItems.add(curr.getDescription());
                }
                
                inputOptions.add(curr.getName());
            }
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");
        inputOptions.add("q");

        ui.showList("Wearable Items", wearableItems);
        choice = ui.inputFrom(
            "Select item to wear, or cancel",
            inputOptions);

        if(!(choice.equals("exit") || choice.equals("cancel") ||
            choice.equals("q")))
        {
            player.wear(choice);
            ui.log("Equipped " + choice);
            
            ui.inputUnchecked("Press Enter to continue");
        }
    }
}