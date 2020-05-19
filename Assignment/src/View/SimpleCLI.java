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
    public String selectFromSet(String prompt, Set<String> set)
    {
        String in = null;
        boolean match = false;
        Iterator<String> iter = set.iterator();

        // TODO clean up :)

        while(!match)
        {
            System.out.println(prompt);
            in = sc.nextLine();

            while(iter.hasNext() && !match)
            {
                if(iter.next().contains(in))
                {
                    match = true;
                }
            }
            
            if(!match)
            {
                System.out.println("Invalid input");
            }
        }

        return in;
    }

    
}