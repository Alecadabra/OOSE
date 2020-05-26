package View;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SimpleCLI implements UserInterface
{
    private static final String C_CLEAR_SCREEN = "\033[H\033[2J";
    private static final String C_CYAN = "\u001B[36m";
    //private static final String C_YELLOW = "\u001B[33m";
    private static final String C_BOLD = "\033[0;1m";
    private static final String C_LINE = "\033[0;4m";
    private static final String C_RESET = "\u001B[0m";

    Scanner sc;

    public SimpleCLI()
    {
        sc = new Scanner(System.in);
    }

    @Override
    public void clear()
    {
        System.out.print(C_CLEAR_SCREEN + C_RESET + "\n\n" + C_RESET);
    }
    
    @Override
    public void heading(String heading)
    {
        System.out.println("\n" + 
            C_CYAN + "------------------------------" + C_RESET + "\n" +
            C_BOLD + "  " + heading + C_RESET + "\n" +
            C_CYAN + "------------------------------" + C_RESET + "\n");
    }

    @Override
    public void display(String content)
    {
        System.out.println("\n" + C_LINE + content + C_RESET);
    }

    @Override
    public void log(String message)
    {
        System.out.println("  - " + message);
    }

    @Override
    public void showList(String title, List<?> list)
    {
        display(title);

        for(Object o : list)
        {
            System.out.println("  - " + o.toString());
        }
    }

    @Override
    public String inputFrom(String prompt, List<?> options)
    {
        String key = "";
        Iterator<?> iter;
        String match = null;
        String curr, orig;

        while(match == null)
        {
            System.out.print("\n" + C_LINE +  prompt + C_RESET + ":\n  > ");
            key = "";
            while(key == "")
            {
                if(sc.hasNextLine())
                {
                    key = sc.nextLine().toLowerCase().trim();
                }
            }
            iter = options.iterator();

            while(iter.hasNext() && match == null)
            {
                orig = iter.next().toString();
                curr = orig.toLowerCase().trim();

                if(key.contains(curr))
                {
                    match = orig;
                }
            }
            
            if(match == null)
            {
                System.out.println("    Invalid input");
            }
        }

        return match;
    }

    @Override
    public String inputUnchecked(String prompt)
    {
        System.out.print("\n" + C_LINE +  prompt + C_RESET + ":\n    ");

        return sc.nextLine().trim();
    }    
}