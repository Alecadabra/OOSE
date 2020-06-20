package Q2;

public class GameLoader
{
    private Parser parser;
    
    public GameLoader(Parser parser)
    {
        this.parser = parser;
    }

    public Game load() throws GameLoadException
    {
        Element[] elements;
        Game game;

        elements = parser.parse();
        game = new Game(elements);

        return game;
    }
}