package View;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SimpleCLI implements UserInterface
{
    Scanner sc;

    public SimpleCLI()
    {
        sc = new Scanner(System.in);
    }   
    
    @Override
    public void log(String message)
    {
        System.out.println("message");
    }

    @Override
    public void showList(String title, List<String> list)
    {
        System.out.println(title);

        for(String s : list)
        {
            System.out.println(s);
        }
    }

    @Override
    public String inputFrom(String prompt, List<String> options)
    {
        String in = null;
        Iterator<String> iter = options.iterator();
        String match = null;
        String curr;

        // TODO clean up :)

        while(match == null)
        {
            System.out.print(prompt + ": ");
            in = sc.nextLine().toLowerCase().trim();

            while(iter.hasNext() && match == null)
            {
                curr = iter.next().toLowerCase().trim();

                if(curr.contains(in))
                {
                    match = curr;
                }
            }
            
            if(match == null)
            {
                System.out.println("Invalid input");
            }
        }

        return in;
    }

    @Override
    public String inputUnchecked(String prompt)
    {
        String in = null;

        System.out.print(prompt + ": ");
        in = sc.nextLine().trim();

        
    }

    @Override
    public void showWelcome()
    {
        System.out.println("Welcome!");
    }

    @Override
    public void showGoodbye()
    {
        System.out.println("Goodbye!");
    }

    
}