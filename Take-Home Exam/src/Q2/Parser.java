package Q2;

import java.util.LinkedList;

public class Parser
{
    private Lexer lexer;
    private ElementFactory elementFactory;

    public Parser(Lexer lexer, ElementFactory elementFactory)
    {
        this.lexer = lexer;
        this.elementFactory = elementFactory;
    }

    public Element[] parse() throws GameLoadException
    {
        LinkedList<Element> elementsList = new LinkedList<>();
        String name;
        int x, y;

        name = lexer.nextToken();
        while(name != null)
        {
            try
            {
                x = Integer.parseInt(lexer.nextToken());
                y = Integer.parseInt(lexer.nextToken());
            }
            catch(NumberFormatException e)
            {
                throw new GameLoadException("Invalid x/y values");
            }

            elementsList.add(elementFactory.createElement(name, x, y));
        }

        if(elementsList.isEmpty())
        {
            throw new GameLoadException("No elements to load");
        }

        return (Element[])elementsList.toArray();
        
    }
}