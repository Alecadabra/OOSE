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
import Model.Items.ItemStorage;
import View.UserInterface;

/**
 * Battle screen, puts the player character against an enemy from the enemy
 * factory stored in the class.
 */
public class Battle extends ControllerScreen
{
    private EnemyFactory enemyFactory;

    /**
     * Constructor.
     * @param ui The user interface to use
     * @param player The player character interacting with the screen
     * @param enemyFactory The initialised enemy factory to use to get enemies
     */
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
            // Get the enemy and give the characters the ui object so they can
            // use it whenever they do anything noteworthy
            enemy = enemyFactory.getEnemy();
            enemy.setLog(ui);
            player.setLog(ui);

            ui.show(String.format("%s vs. %s",
                player.getName(), enemy.getName()));
            
            while(player.getHp() > 0 && enemy.getHp() > 0)
            {
                ui.show(String.format(
                    "It's %s's turn", player.getName()));

                // Have the player perform their desired action
                playerAction(enemy);

                if(player.getHp() > 0 && enemy.getHp() > 0)
                {
                    ui.show(String.format(
                        "It's %s's turn", enemy.getName()));
                    enemy.attack(player);
                }
            }

            if(player.getHp() == 0)
            {
                ui.show(player.getName() + " has been defeated");
                ui.show("Game Over :(");
            }
            else if(enemy.getHp() == 0)
            {
                if(enemyFactory.isBoss(enemy))
                {
                    ui.show(String.format(
                        "%s has defeated the %s - You win the game!",
                        player.getName(), enemy.getName()));
                    player.kill();
                }
                else
                {
                    ui.show(String.format(
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
            // Shouldn't ever occur
            ui.log("There's nobody to fight; " + e.getMessage());
        }
        catch(CharacterException e)
        {
            ui.log("Something went wrong; " + e.getMessage());
        }

        ui.inputUnchecked("Press Enter to exit fight");
    }

    /**
     * Has the player perform a desired action (Or cancel).
     * @param enemy The enemy that the player can choose to attack or use an
     * item on
     * @throws CharacterException If something went wrong
     */
    private void playerAction(Enemy enemy) throws CharacterException
    {
        ItemStorage inv = player.getInventory();
        List<Item> usableItems = inv.getAllUsable();
        List<String> inputsUsables = inv.getAllUsableNames();
        List<String> inputs = Arrays.asList(
            "attack", "hit", "fight", "a",
            "use", "potion", "u"
        );
        boolean chosen = false;
        String choice;
        Item usableItem;
        GameCharacter usableItemTarget;

        while(!chosen)
        {
            choice = ui.inputFrom(String.format(
                "You can use an item, or attack with %s",
                player.getEquippedName()),
                inputs);
                
            if(choice == null)
            {
                // If the player chose to exit, set to something that the
                // switch statement won't complain about
                choice = "";
                ui.log("You cannot flee");
            }

            switch(choice)
            {
                case "attack":
                case "hit":
                case "fight":
                case "a":
                {
                    // Attack with equipped item
                    player.attack(enemy);
                    chosen = true;
                    break;
                }
                case "use":
                case "potion":
                case "u":
                {
                    // Use an item
                    usableItem = selectItem(usableItems, inputsUsables);
                    if(usableItem != null)
                    {
                        // If the user selected an item (Rather than cancelling)
                        usableItemTarget = selectTarget(
                            usableItem, Arrays.asList(player, enemy));
                        if(usableItemTarget != null)
                        {
                            // If the user selected a target (Rather than
                            // cancelling)
                            usableItemTarget.use(usableItem);
                            inv.remove(usableItem.getName());
                            chosen = true;
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Has the player select a usable item (Or cancel).
     * @param itemsList List of possible items to choose from
     * @param inputs Input options for the user
     * @return The chosen item, null if cancelled
     */
    private Item selectItem(List<Item> itemsList, List<String> inputs)
    {
        String choice;
        Item usableItem = null;
        ItemStorage inv = player.getInventory();

        if(itemsList.isEmpty())
        {
            ui.log("You do not have any usable items");
        }
        else
        {
            ui.showList("Usable Items", itemsList);
            choice = ui.inputFrom("Select an item to use, or cancel", inputs);

            if(choice != null)
            {
                usableItem = inv.get(choice);
            }
        }
        
        return usableItem;
    }

    /**
     * Have the user select a character to use an item on (Or cancel).
     * @param usableItem The item to use (For display purposes)
     * @param characters A list of possible characters to use the item on
     * @return The chosen GameCharacter, null if cancelled
     */
    private GameCharacter selectTarget(Item usableItem,
        List<GameCharacter> characters)
    {
        String choice;
        GameCharacter chosen = null;
        ArrayList<String> inputs = new ArrayList<>();

        for(GameCharacter curr : characters)
        {
            // Build an input options list
            inputs.add(curr.getName());
        }

        ui.showList("Targets", characters);
        choice = ui.inputFrom("Select a target, or cancel", inputs);

        if(choice != null)
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