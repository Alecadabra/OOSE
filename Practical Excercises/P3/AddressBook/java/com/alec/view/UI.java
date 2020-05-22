package view;

import java.util.Scanner;

import controller.OptionHandler;

public class UI
{
    public static void printErr(String message)
    {
        System.out.println("Error: " + message);
    }

    public static void printMessage(String message)
    {
        System.out.println("Alert: " + message);
    }

    public static String input(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.print("Alert: " + prompt + "\n> ");

        input = sc.nextLine();

        return input;
    }

    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    public static void showMenu(OptionHandler optHandler)
    {
        boolean done = false;
        String search, result;
        int menu;

        while(!done)
        {
            try
            {
                menu = Integer.parseInt(
                    input("(1) Search by name, (2) Search by email, (3) Quit"));

                switch(menu)
                {
                    case 1:
                        // Search for emails by name

                        search = input("Enter name");
                        result = optHandler.doOption(0, search);
                        printMessage("Result found:\n" + result);
                        
                        break;
                        
                    case 2:
                        // Search for names by email

                        search = input("Enter email");
                        result = optHandler.doOption(1, search);
                        printMessage("Result found:\n" + result);
                        
                        break;
                        
                    case 3:
                        // Exit case

                        done = true;
                        break;
                }
            }
            catch(NumberFormatException e1)
            {
                // The user entered something non-numerical.
                printErr("Invalid input: " + e1.getMessage());
            }
            catch(IllegalArgumentException e2)
            {
                // Problem with doOption()
                printErr(e2.getMessage());
            }
        }
    }
}