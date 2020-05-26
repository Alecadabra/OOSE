package Controller.ControllerScreens;

import java.util.ArrayList;
import java.util.List;

import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import View.UserInterface;

public class WeaponSelection extends ControllerScreen
{
    public WeaponSelection(UserInterface ui, PlayerCharacter player)
    {
        super("Weapon Selection", ui, player);
    }

    @Override
    void execute()
    {
        List<Item> invItems = player.getAllItems();
        ArrayList<String> equippableItems = new ArrayList<>();
        ArrayList<String> inputOptions = new ArrayList<>();
        String choice;

        for(Item curr : invItems)
        {
            if(curr.isEquipabble())
            {
                if(curr.getName().contains("["))
                {
                    // Enchanted item - add tip on how to select
                    equippableItems.add(curr.getDescription() +
                        " *Enter '" + curr.getName() + "'*");
                }
                else
                {
                    equippableItems.add(curr.getDescription());
                }
                
                inputOptions.add(curr.getName());
            }
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");
        inputOptions.add("q");

        ui.showList("Equippable Items", equippableItems);
        choice = ui.inputFrom(
            "Select item to equip, or cancel.",
            inputOptions);

        if(!(choice.equals("exit") || choice.equals("cancel") ||
            choice.equals("q")))
        {
            player.equip(choice);
            ui.log("Equipped " + choice);

            ui.inputUnchecked("Press Enter to continue");
        }
    }
}