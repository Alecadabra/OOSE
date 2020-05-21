import java.util.Scanner;

import Controller.ConfigHandler;
import Controller.Controller;
import Controller.Shop;
import View.UserInterface;
import View.UserInterfaceException;

public class FighterGame
{
    public static void main(String[] args)
    {
        UserInterface ui;
        ConfigHandler config;
        String configFile;
        Controller controller;
        Shop shop;

        if(args.length == 0)
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter config file name " +
                "(Can be given in command-line arguments): ");
            configFile = sc.nextLine();
            sc.close();
        }
        else
        {
            configFile = args[0];
        }

        try
        {
            config = new ConfigHandler(configFile);

            ui = config.getUI();
            shop = new Shop(config.getShopLoader());

            controller = new Controller(ui, shop);
        }
        catch(Exception e)
        {
            try
            {
                // TODO Error message through UI
                throw new UserInterfaceException("Stub");
            }
            catch(UserInterfaceException ee)
            {
                // Just print it without the fancy UI
                System.out.println("Error - " + e.getMessage());
            }
        }
    }
}
