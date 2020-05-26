package Controller.ControllerScreens;

import java.util.ArrayList;
import java.util.List;

import Model.Characters.CharacterException;
import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import View.UserInterface;

public class Sell extends ControllerScreen
{
    public Sell(UserInterface ui, PlayerCharacter player)
    {
        super("Sell Item", ui, player);
    }

    void execute()
    {
        List<Item> inv = player.getAllItems();
        ArrayList<String> itemsWithPrices = new ArrayList<>();
        ArrayList<String> inputOptions = new ArrayList<>();
        String choice;
        Item item;

        for(Item curr : inv)
        {
            if(!(player.isEquipped(curr.getName()) ||
                player.isWearing(curr.getName())))
            {
                itemsWithPrices.add(String.format("%s (%d gold sell price)",
                    curr.getDescription(), curr.getSell()));
                inputOptions.add(curr.getName());
            }
        }

        if(itemsWithPrices.isEmpty())
        {
            ui.log("You do not have any items to sell");
        }
        else
        {
            inputOptions.add("exit");
            inputOptions.add("cancel");
            inputOptions.add("q");

            ui.showList("Items to sell", itemsWithPrices);
            choice = ui.inputFrom(
                "Enter the name of an item to sell, or cancel.",
                inputOptions);

            if(!(choice.equals("exit") || choice.equals("cancel") ||
                choice.equals("q")))
            {
                try
                {
                    item = player.removeItem(choice);
                    player.addGold(item.getSell());
                    ui.log(String.format(
                        "Sold %s for %d gold. You now have %d gold",
                        item.getName(), item.getSell(), player.getGold()));
                }
                catch(CharacterException e)
                {
                    ui.log("Couldn't get item, you have not been charged");
                }
                
                ui.inputUnchecked("Press Enter to continue");
            }
        }
    }
}