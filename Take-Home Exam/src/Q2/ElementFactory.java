package Q2;

public class ElementFactory
{
    public Element createElement(String name, int x, int y)
        throws GameLoadException
    {
        Element elem;

        switch(name)
        {
            case "Brick":
            {
                elem = new Brick(x,y);
                break;
            }
            case "Character":
            {
                elem = new Character(x,y);
                break;
            }
            case "Enemy":
            {
                elem = new Enemy(x,y);
                break;
            }
            default:
            {
                throw new GameLoadException("Invalid Element Type '" + name + "'");
            }
        }

        return elem;
    }
}