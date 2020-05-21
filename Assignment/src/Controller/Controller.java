package Controller;

import View.UserInterface;

public class Controller
{
    private UserInterface ui;
    private Shop shop;
    
    public Controller(UserInterface ui, Shop shop)
    {
        this.ui = ui;
        this.shop = shop;
    }
}