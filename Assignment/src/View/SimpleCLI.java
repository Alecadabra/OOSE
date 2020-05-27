package View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A simple command line user interface. Clears the terminal on clear(),
 * uses some simple colours to emphasise text.
 */
public class SimpleCLI implements UserInterface
{
    // Lambdas that modify the colour of a given string
    private final Function<String, String> cyan =
        s -> "\u001B[36m" + s + "\u001B[0m";
    private final Function<String, String> yellow =
        s -> "\u001B[33m" + s + "\u001B[0m";
    private final Function<String, String> bold =
        s -> "\033[0;1m" + s + "\u001B[0m";
        
    // Resets the colour back to normal
    private final Function<Void, String> reset = s -> "\u001B[0m";

    // CLears the terminal
    private final Function<Void, String> clear = s -> "\033[H\033[2J";

    // Scanner
    private Scanner sc;

    public SimpleCLI()
    {
        sc = new Scanner(System.in);
    }

    @Override
    public void clear()
    {
        System.out.print(clear.apply(null) + "\n\n" + reset.apply(null));
    }
    
    @Override
    public void heading(String heading)
    {
        System.out.println("\n" + 
            cyan.apply("------------------------------") + "\n" +
            "  " + bold.apply(heading) + "\n" +
            cyan.apply("------------------------------"));
    }

    @Override
    public void show(String content)
    {
        System.out.println(
            "\n" + cyan.apply("[ ") + content + cyan.apply(" ]"));
    }

    @Override
    public void log(String message)
    {
        System.out.println("  " + yellow.apply("- ") + message);
    }

    @Override
    public void showList(String title, List<?> list)
    {
        show(title);

        for(Object o : list)
        {
            System.out.println("  " + yellow.apply("* ") + o.toString());
        }
    }

    @Override
    public String inputFrom(String prompt, List<String> options)
    {
        String key;
        Iterator<?> iter;
        String match = null;
        String curr, orig;

        // Make the list modifiable
        if(options instanceof ArrayList)
        {
            options = (ArrayList<String>)options;
        }
        else if(options instanceof LinkedList)
        {
            options = (LinkedList<String>)options;
        }
        else
        {
            options = new ArrayList<>(options);
        }

        // Add options to cancel/exit or get list of options
        options.add("Cancel");
        options.add("Exit");
        options.add("?");

        while(match == null)
        {
            show(prompt);
            System.out.print(yellow.apply("  > "));
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
                log("Invalid input, enter '?' to get possible options");
            }
            else if(match.equals("?"))
            {
                showList("Possible options, case does not matter", options);
                match = null;
            }
        }

        if(match.equals("Cancel") || match.equals("Exit"))
        {
            match = null;
        }

        return match;
    }

    @Override
    public String inputUnchecked(String prompt)
    {
        String in = "";

        sc = new Scanner(System.in);

        show(prompt);
        System.out.print(yellow.apply("  > "));

        if(sc.hasNextLine())
        {
            in = sc.nextLine().trim();
        }

        return in;
    }    
}