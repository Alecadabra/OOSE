Alec Maughan | OOSE Take-Home Exam S1 2020

Formatted to be viewed in a Markdown flavour that supports syntax-highlighted code blocks :)

# Question 1

## Overview

### Subjects

PhysicsEngineSubject.java

PlayerActionSubject.java

### Actions that can occur

A player chooses to use a bonus ite from their inventory (From PlayerActionSubject)

A player collides with a bonus item in the game world (From PhysicsEngineSubject)

A player collides with some other item in the game world (From PhysicsEngineSubject)

### Observer Interfaces

CollisionObserver.java

BonusItemObserver.java

### Concrete Observers

BonusItemConcreteObserver.java (Player using a bonus item)

PlayerCollisionConcreteObserver.java (Player colliding with stuff)

### Other Model Classes (Invoked but not shown in code)

BonusItem.java

GameObject.java

PlayerCharacter.java

## Implementation

### Subjects

#### PhysicsEngineSubject.java

```java
public class PhysicsEngineSubject
{
    private Set<CollisionObserver> collObs = new HashSet<>();
    private Set<BonusItemObserver> bonusObs = new HashSet<>();

    ...

    public void addObserver(CollisionObserver ob)
    {
        collObs.add(ob);
    }

    public void addObserver(BonusItemObserver ob)
    {
        bonusObs.add(ob);
    }

    private void collision(GameObject obj1, GameObject obj2, float velocity)
    {
        // If the collision was between a player and a bonus item, notify the bonus item observers
        // Otherwise, notify the standard collision observsers

        if(obj1 instanceof PlayerCharacter && obj2 instanceof BonusItem)
        {
            notifyBonusItemObservers((PlayerCharacter)obj1, (BonusItem)obj2);
        }
        else if(obj2 instanceof PlayerCharacter && obj1 instanceof BonusItem)
        {
            notifyBonusItemObservers((PlayerCharacter)obj2, (BonusItem)obj1);
        }
        else
        {
            notifyCollisionObservers(obj1, obj2, velocity);
        }
    }

    private void notifyCollisionObservers(GameObject obj1, GameObject obj2, float velocity)
    {
        for(CollisionObserver ob : collObs)
        {
            ob.objectCollision(obj1, obj2, velocity);
        }
    }

    private void notifyBonusItemObservers(PlayerCharacter player, BonusItem item)
    {
        for(BonusItemObserver ob : bonusObs)
        {
            ob.itemUsed(player, item);
        }
    }

    ...
}
```

#### PlayerActionSubject.java

```java
public class PlayerActionSubject
{
    private Set<BonusItemObserver> bonusObs = new HashSet<>();
    private PlayerCharacter player;

    ...

    public void addObserver(BonusItemObserver ob)
    {
        bonusObs.add(ob);
    }

    public void useBonusItemFromInventory(BonusItem item)
    {
        notifyObservers(item);
    }

    private void notifyObservers(BonusItem item)
    {
        for(BonusItemObserver ob : bonusObs)
        {
            ob.itemUsed(this.player, item);
        }
    }

    ...
}
```

### Observer Interfaces

#### CollisionObserver.java

```java
public interface CollisionObserver
{
    // For standard collisions between game objects in the game world

    public void objectCollision(GameObject obj1, GameObject obj2, float velocity);
}
```

#### BonusItemObserver.java

```java
public interface BonusItemObserver
{
    // For times when a player uses an item

    public void itemUsed(PlayerCharacter player, BonusItem item);
}
```

### Concrete Observers

#### BonusItemConcreteObserver.java

```java
package Q1;

public class BonusItemConcreteObserver implements BonusItemObserver
{
    // Has the player use the item

    @Override
    public void itemUsed(PlayerCharacter player, BonusItem item)
    {
        player.useItem(item);
        item.startTimer(player, item, this);
        item.removeFromGame();
    }
}
```

#### PlayerCollisionConcreteObserver.java

```java
public class PlayerCollisionConcreteObserver implements CollisionObserver
{
    @Override
    public void objectCollision(GameObject obj1, GameObject obj2, float velocity)
    {
        PlayerCharacter player = null;

        if(velocity > 10 && (obj1 instanceof PlayerCharacter || obj2 instanceof PlayerCharacter))
        {
            if(obj1 instanceof PlayerCharacter)
            {
                player = (PlayerCharacter)obj1;
            }
            else if(obj2 instanceof PlayerCharacter)
            {
                player = (PlayerCharacter)obj2;
            }

            player.takeDamage((int)velocity);
        }
    }    
}
```

# Question 2

## Overview

GameLoader still just aggregates a single Parser.

Parser aggregates a single Lexer and also an ElementFactory.

ElementFactory is non-static so that it can be part of Parser's dependency injection.

## Implementation

### GameLoader.java

```java
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
```

### Parser.java

```java
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
```

### ElementFactory.java

```java
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
```