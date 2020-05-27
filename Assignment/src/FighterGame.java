import java.util.Scanner;

import Controller.ConfigHandler;
import Controller.Controller;
import Controller.EnchantmentHandler;
import Model.Items.ItemStorage;
import View.UserInterface;

public class FighterGame
{
    public static void main(String[] args)
    {
        Scanner sc = null;
        UserInterface ui = null;
        ConfigHandler config;
        String configFile;
        Controller controller;
        ItemStorage shopStorage;
        EnchantmentHandler enchantmentHandler;

        if(args.length == 0)
        {
            sc = new Scanner(System.in);
            System.out.print("Enter config file name " +
                "(Can be given in command-line arguments): ");
            configFile = sc.nextLine();
        }
        else
        {
            configFile = args[0];
        }

        try
        {
            // Start the config handler
            config = new ConfigHandler(configFile);

            // Get the user interface
            ui = config.getUI();

            enchantmentHandler = config.getEnchantmentHandler();

            // Set up shop - get the loader from confighandler, get the
            // enchantment handler, make a new shop storage, load the data 
            // into the shopstorage
            shopStorage = new ItemStorage();
            shopStorage.add(config.getShopLoader().load());
            shopStorage.add(enchantmentHandler.load());

            // Set up the controller and run the game!
            controller = new Controller(
                ui,
                config.getPlayerCharacter(),
                config.getEnemyFactory(),
                shopStorage,
                enchantmentHandler
            );
            controller.execute();
        }
        catch(Exception e)
        {
            // Last ditch exception handling
            try
            {
                // Try print the error through the UI if it's currently working
                ui.show("A fatal error occured: " + e.getMessage());
            }
            catch(Exception ee)
            {
                // Just print it without the fancy UI
                System.out.println("A fatal error occured: " + e.getMessage());

                // TODO remove
                System.out.println();
                e.printStackTrace();
            }
        }

        if(sc != null)
        {
            // Close the scanner if it was opened
            sc.close();
        }
    }
}
