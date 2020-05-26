package Controller.ControllerScreens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.EnemyFactory;
import Controller.EnemyFactoryException;
import Model.Characters.CharacterException;
import Model.Characters.Enemy;
import Model.Characters.GameCharacter;
import Model.Characters.PlayerCharacter;
import Model.Items.Item;
import View.UserInterface;

public class Battle extends ControllerScreen
{
    private EnemyFactory enemyFactory;

    public Battle(UserInterface ui, PlayerCharacter player,
        EnemyFactory enemyFactory)
    {
        super("Battle", ui, player);
        this.enemyFactory = enemyFactory;
    }

    @Override
    void execute()
    {
        Enemy enemy;

        try
        {
            enemy = enemyFactory.getEnemy();
            enemy.setLog(ui);
            player.setLog(ui);

            ui.display(String.format("%s vs. %s",
                player.getName(), enemy.getName()));
            
            while(player.getHp() > 0 && enemy.getHp() > 0)
            {
                playerAction(enemy);

                if(player.getHp() > 0 && enemy.getHp() > 0)
                {
                    ui.display(String.format(
                        "It's %s's turn", enemy.getName()));
                    enemy.attack(player);
                }
            }

            if(player.getHp() == 0)
            {
                ui.display(String.format(
                    "%s has been defeated by %s",
                    player.getName(), enemy.getName()));
                ui.display("Game Over :(");
            }
            else if(enemy.getHp() == 0)
            {
                if(enemyFactory.isBoss(enemy))
                {
                    ui.display(String.format(
                        "%s has defeated the %s - You win the game!",
                        player.getName(), enemy.getName()));
                    player.kill();
                }
                else
                {
                    ui.display(String.format(
                        "%s has defeated the %s",
                        player.getName(), enemy.getName()));

                    player.addGold(enemy.getGold());
                    ui.log(String.format(
                        "You have been awarded %d gold!", enemy.getGold()));
                    
                    player.heal((int)(player.getHp() * 1.5));
                }
            }
        }
        catch(EnemyFactoryException e)
        {
            ui.log("There's nobody to fight; " + e.getMessage());
        }
        catch(CharacterException e)
        {
            ui.log("Something went wrong; " + e.getMessage());
        }

        ui.inputUnchecked("Press Enter to exit fight");
    }

    private void playerAction(Enemy enemy) throws CharacterException
    {
        String choice;
        List<Item> invItems = player.getAllItems();
        ArrayList<Item> usableItemsList = new ArrayList<>();
        ArrayList<String> usableItemInputOptions = new ArrayList<>();
        List<String> inputOptions = Arrays.asList(
            "attack", "hit", "fight",
            "use", "potion"
        );
        boolean chosen = false;
        Item usableItem;
        GameCharacter usableItemTarget;

        // Check if they have any usable items
        for(Item curr : invItems)
        {
            if(curr.isUsable())
            {
                usableItemsList.add(curr);
                usableItemInputOptions.add(curr.getName());
            }
        }

        if(usableItemsList.isEmpty())
        {
            // Player can only attack
            ui.inputUnchecked(
                "It's your turn, Press Enter to attack with " +
                player.getEquippedName());
            player.attack(enemy);
        }
        else
        {
            while(!chosen)
            {
                choice = ui.inputFrom(String.format(
                    "It's your turn, you can attack with %s, or use an item",
                    player.getEquippedName()),
                    inputOptions);
                switch(choice)
                {
                    case "attack":
                    case "hit":
                    case "fight":
                    {
                        player.attack(enemy);
                        chosen = true;
                        break;
                    }
                    case "use":
                    case "potion":
                    {
                        usableItem = selectItem(
                            usableItemsList, usableItemInputOptions);
                        if(usableItem != null)
                        {
                            usableItemTarget = selectTarget(
                                usableItem, Arrays.asList(player, enemy));
                            if(usableItemTarget != null)
                            {
                                usableItemTarget.use(usableItem);
                                try
                                {
                                    player.removeItem(usableItem.getName());
                                    chosen = true;
                                }
                                catch(CharacterException e)
                                {
                                    ui.log("Error; " + e.getMessage());
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private Item selectItem(List<Item> itemsList, List<String> inputOptions)
    {
        String choice;
        Item usableItem = null;

        inputOptions.add("exit");
        inputOptions.add("cancel");
        inputOptions.add("q");

        ui.showList("Usable Items", itemsList);
        choice = ui.inputFrom(
            "Select an item to use, or cancel", inputOptions);

        if(!(choice.equals("exit") || choice.equals("cancel") ||
            choice.equals("q")))
        {
            for(Item curr : itemsList)
            {
                if(curr.getName().equals(choice))
                {
                    usableItem = curr;
                }
            }

            if(usableItem == null)
            {
                ui.log("Error getting item from inventory");
            }
        }
        
        return usableItem;
    }

    private GameCharacter selectTarget(Item usableItem,
        List<GameCharacter> characters)
    {
        String choice;
        GameCharacter chosen = null;
        ArrayList<String> inputOptions = new ArrayList<>();

        for(GameCharacter curr : characters)
        {
            inputOptions.add(curr.getName());
        }

        inputOptions.add("exit");
        inputOptions.add("cancel");
        inputOptions.add("q");

        ui.showList("Targets", characters);
        choice = ui.inputFrom(
            "Select a target, or cancel", inputOptions);

        if(!(choice.equals("exit") || choice.equals("cancel") ||
            choice.equals("q")))
        {
            for(GameCharacter curr : characters)
            {
                if(curr.getName().equals(choice))
                {
                    chosen = curr;
                }
            }
        }

        return chosen;
    }
}