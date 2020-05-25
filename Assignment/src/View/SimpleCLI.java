package View;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SimpleCLI implements UserInterface
{
    Scanner sc;

    public SimpleCLI()
    {
        sc = new Scanner(System.in);
    }   
    
    @Override
    public void heading(String heading)
    {
        System.out.println("\n-------------------------\n " + heading + 
            "\n-------------------------\n");
    }

    @Override
    public void display(String content)
    {
        System.out.println("\n" + content);
    }

    @Override
    public void log(String message)
    {
        System.out.println("    " + message);
    }

    @Override
    public void showList(String title, List<?> list)
    {
        System.out.println("\n" + title);

        for(Object o : list)
        {
            System.out.println("    " + o.toString());
        }
    }

    @Override
    public String inputFrom(String prompt, List<?> options)
    {
        String key = "";
        Iterator<?> iter;
        String match = null;
        String curr;

        while(match == null)
        {
            System.out.print(prompt + ":\n    ");
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
                curr = iter.next().toString().toLowerCase().trim();

                if(key.contains(curr))
                {
                    match = curr;
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
        System.out.print(prompt + ":\n    ");

        return sc.nextLine().trim();
    }    
}