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
        ArrayList<Item> equippableItems = new ArrayList<>();
        String choice;

        for(Item curr : invItems)
        {
            if(curr.isEquipabble())
            {
                equippableItems.add(curr);
            }
        }

        ui.showList("Equippable Items", equippableItems);
        choice = ui.inputFrom(
            "Select item to equip (Exact name)", equippableItems);
        player.equip(choice);
        ui.log("Equipped " + choice);
    }
}