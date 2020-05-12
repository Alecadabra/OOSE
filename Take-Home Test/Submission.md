Alec Maughan | OOSE Take-Home Test S1 2020

Formatted to be viewed in a Markdown flavour that supports syntax-highlighted code blocks :)

# Question 1

## (a)

```python
class DoubleMap:
    def __init__(self):
        self.map = dict()

    def get(self, key: tuple):
        """
        Returns tuple of two values stored at given key
        """
        if len(key) != 2:
            raise SyntaxError('Must give two object types')
            
        if type(key[0]) == type(key[1]):
            raise TypeError('Values must be different types')

        return self.map[key]
    
    def put(self, key: tuple, data: tuple):
        """
        Puts tuple of two values into map at given two-value key
        """
        if len(key) != 2:
            raise SyntaxError('Must give two objects for key')

        if len(data) != 2:
            raise SyntaxError('Must give two objects for data')

        if type(key[0]) == type(key[1]):
            raise TypeError('Values must be different types')

        if type(data[0]) == type(data[1]):
            raise TypeError('Values must be different types')

        self.map[key] = data

# Demonstration of DoubleMap
map = DoubleMap()

map.put(('One', 1), ('Two', 2))
print(map.get(('One', 1))) # Prints "('Two', 2)"
```

## (b)

```python
class NValueMap:
    def __init__(self):
        self.map = dict()

    def get(self, key: tuple) -> tuple:
        """
        Returns tuple of values stored at given tuple key
        """
        # Check for difference in datatypes of elements of key
        for element in key:
            if type(element) != type(key[0]):
                raise TypeError('All values given must be the same datatype')

        return self.map[key]
    
    def put(self, key: tuple, data: tuple):
        """
        Puts tuple of values into map at given tuple key
        """
        # Check for difference in datatypes of elements of key
        for element in key:
            if type(element) != type(key[0]):
                raise TypeError('All values given must be the same datatype')

        # Check for difference in datatypes of elements of data
        for element in data:
            if type(element) != type(data[0]):
                raise TypeError('All values given must be the same datatype')

        self.map[key] = data

# Demonstration of NValueMap
map = NValueMap()

map.put((1, 2, 3, 4, 5), ('One', 'Two', 'Three', 'Four', 'Five'))
print(map.get((1, 2, 3, 4, 5))) # Prints "('One', 'Two', 'Three', 'Four', 'Five')"
```

## (c)

```java
/**
 * Maps a list of pairs of objects to a single int
 */
public class IntMapper
{
    /**
     * Represents a pair of two objects
     */
    public static class Pair
    {
        private Object obj1;
        private Object obj2;

        public Pair(Object obj1, Object obj2)
        {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        public Object get1()
        {
            return obj1;
        }

        public Object get2()
        {
            return obj2;
        }
    }

    private HashMap<List<Pair>, Integer> map;

    public IntMapper()
    {
        map = new HashMap<>();
    }

    public void put(List<Pair> pair, int num)
    {
        map.put(pair, num);
    }

    public int get(List<Pair> pair)
    {
        return map.get(pair);
    }
}

/**
 * Demonstrate the use of IntMapper
 */
public class TestIntMapper
{
    public static void main(String[] args)
    {
        IntMapper map = new IntMapper();
        ArrayList<IntMapper.Pair> pairs = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
        {
            pairs.add(new IntMapper.Pair(i, Integer.toString(i)));
            // When i==3, add ("3",3) to list for 1 through 10
        }
        map.put(pairs, 42);

        System.out.println(map.get(pairs)); // Prints 42

    }
}
```

# Question 2

## (a)

```java
public class MainWindow
{
    UI box; // DialogBox by default
    Menu menu;
    ToolBar bar;

    ...

    public void launch()
    {
        try
        {
            box.show("Welcome");
        }
        catch(DialogBoxException e)
        {
            // Dialog box failed, fall back to simpler UI
            box = new CommandLineInterface();
            box.show("Welcome");
        }
        
        try
        {
            switch(bar.onClick())
            {
                ...
            }

            box.show("Select an option from the menu");
            menu.runMenu();
        }
        catch(ToolBarException e)
        {
            // Display an error message and exit program naturally, a toolbar
            // failing is catastrophic!
            box.show("An unexpected error occured, " + e.getMessage());
        }
        catch(MenuException e)
        {
            // Display an error message run menu again, menu exceptions are from
            // user error so running it again will not cause the exact same
            // thing to happen
            box.show("An unexpected error occured, " + e.getMessage());
            menu.runMenu();
        }
        finally
        {
            // Close everything, assume these methods are defined
            menu.close();
            bar.close();
        }
    }
}
```

```java
public class DialogBox implements UI
{
    /* 'UI' interface defines abstract show(), there is also a
    'CommandLineInterface' class that implements this interface */

    ...

    @Override public void show(String s) throws DialogBoxException
    {
        Operation1 op1;
        Operation2 op2;

        ...

        try
        {
            ...
            op1.run();
            op2.invoke();
            ...
        }
        catch(DataFormatException e)
        {
            // UI failed to load, thrown by op1.invoke
            throw new DialogBoxException(
                "Data format error loading dialogbox", e);
        }
        catch(NetworkFailException e)
        {
            // Network failed, thrown by op2.invoke
            throw new DialogBoxException(
                "Network error loading dialogbox", e);
        }

        ...
    }
}
```

```java
public class Menu
{
    ...

    public void runMenu() throws MenuException
    {
        Operation1 op1;
        Operation2 op2;
        Operation3 op3;

        ...

        try
        {
            ...
            op1.run();
            op2.invoke();
            op3.start();
            ...
        }
        catch(DataFormatException e)
        {
            // Menu failed to load, thrown by op1.invoke
            throw new MenuException(
                "Data format error loading menu", e);
        }
        catch(NetworkFailException e)
        {
            // Network failed, thrown by op2.invoke
            throw new MenuException(
                "Network error loading menu", e);
        }
        catch(DatabaseSqlException e)
        {
            // Database error, thrown by op3.start
            throw new MenuException(
                "Database error loading menu", e);
            )
        }

        ...
    }
}
```

```java
public class Toolbar
{
    public String onClick() throws ToolBarException
    {
        Operation2 op2;
        Operation3 op3;

        ...

        try
        {
            ...
            op2.invoke();
            op3.start();
            ...
        }
        catch(NetworkFailException e)
        {
            // Network failed, thrown by op2.invoke
            throw new ToolBarException(
                "Network error loading toolbar", e);
        }
        catch(DatabaseSqlException e)
        {
            // Database error, thrown by op3.start
            throw new ToolBarException(
                "Database error loading toolbar", e);
            )
        }

        ...
    }
}
```

## (b)

Controller (MainWindow, Operation1, Operation2, Operation3)

View (DialogBox, Menu, ToolBar, DialogBoxException, MenuException, ToolBarException)

Assume DataFormatException, NetworkFailException & DatabaseSqlException are defined and packaged elsewhere, if not, put them in Controller.

# Question 3

Journey.java
```java
package JourneyPlanner;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import JourneyPlanner.PackagingMaterial.PackagingStrategy;
import JourneyPlanner.TransportMethod.TransportStrategy;

/**
 * Represents a journey taken by a package over a given distance, using a
 * specific packaging material and over 1 or more transport methods
 */
public class Journey
{
    private int distance;
    private PackagingStrategy packaging;
    private LinkedList<TransportStrategy> transportMethods;

    /**
     * Constructor with distance field
     * @param distance Distance of journey, kilometres
     * @param packaging Packaging material of type PackagingStrategy
     * @param transportStrategies Varargs of transport strategies to use, in 
     * order
     */
    public Journey(int distance, PackagingStrategy packaging,
        TransportStrategy... transportStrategies)
    {
        this.distance = distance;
        this.packaging = packaging;
        this.transportMethods = new LinkedList<>();
        for(TransportStrategy strat : transportStrategies)
        {
            this.transportMethods.addLast(strat);
        }
    }

    /**
     * Alternate constructor without distance field
     * @param packaging Packaging material of type PackagingStrategy
     * @param transportStrategies Varargs of transport strategies to use, in 
     * order
     */
    public Journey(PackagingStrategy packaging,
        TransportStrategy... transportStrategies)
    {
        this.distance = 0;
        this.packaging = packaging;
        this.transportMethods = new LinkedList<>();
        for(TransportStrategy strat : transportStrategies)
        {
            this.transportMethods.addLast(strat);
        }
    }

    /**
     * Gets the distance for the journey in kilometres
     * @return Distance: int
     * @throws NoSuchElementException If no distance specified
     */
    public int getDistance() throws NoSuchElementException
    {
        if(this.distance == 0)
        {
            throw new NoSuchElementException("No distance specified");
        }

        return this.distance;
    }
    
    /**
     * Get estimated time taken to complete journey in hours using transport
     * strategys over the distance
     * @return time: int
     * @throws NoSuchElementException If no distance specified
     */
    public int timeTaken() throws NoSuchElementException
    {
        int time = 0;

        if(this.distance == 0)
        {
            throw new NoSuchElementException("No distance specified");
        }

        for(TransportStrategy strat : this.transportMethods)
        {
            time += strat.timeTaken(this.distance);
        }

        return time;
    }

    /**
     * Get estimated proportion of package remaining using the packaging
     * material from 0-1
     * @return proportion: float
     */
    public float proportionRemaining()
    {
        return this.packaging.proportionRemaining();
    }
}
```

PackagingMaterial/PackagingStrategy.java
```java
package JourneyPlanner.PackagingMaterial;

/**
 * Strategy pattern interface for a packaging material
 */
public interface PackagingStrategy
{
    public float proportionRemaining();
}
```

PackagingMaterial/UnPackaged.java
```java
package JourneyPlanner.PackagingMaterial;

public class UnPackaged implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.2f;
    }
}
```

PackagingMaterial/CardboardBox.java
```java
package JourneyPlanner.PackagingMaterial;

public class CardboardBox implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.6f;
    }
}
```

PackagingMaterial/BubbleWrap.java
```java
package JourneyPlanner.PackagingMaterial;

public class BubbleWrap implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.9f;
    }    
}
```

TransportMethod/TransportStrategy.java
```java
package JourneyPlanner.TransportMethod;

/**
 * Strategy pattern interface for a transport method
 */
public interface TransportStrategy
{
    public int timeTaken(int distance);
}
```

TransportMethod/Freight.java
```java
package JourneyPlanner.TransportMethod;

public class Freight implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        return 12 + distance * 2;
    }
}
```

TransportMethod/Plane.java
```java
package JourneyPlanner.TransportMethod;

public class Plane implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        return 15 + distance / 3;
    }
}
```

TransportMethod/Teleport.java
```java
package JourneyPlanner.TransportMethod;

public class Teleport implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        // Distance doesn't matter with teleporting!
        return 1;
    }
}
```

# Question 4

CompositeMinion.java
```java
public abstract class CompositeMinion
{
    String name;

    public CompositeMinion(String name)
    {
        super();
        this.name = name;
    }

    public abstract String getName();

    public abstract DecoratorMinion findMinion(String name);

    public abstract MinionGroup findGroup(String name);

    /**
     * Instruct minions to create evil plan
     */
    public abstract void instructCreateEvilPlan();

    /**
     * Instruct minions to construct death ray
     */
    public abstract void instructConstructDeathRay();
}
```

MinionGroup.java
```java
import java.util.HashMap;
import java.util.Iterator;

public class MinionGroup extends CompositeMinion
{
    HashMap<String, CompositeMinion> minions;

    public MinionGroup(String name)
    {
        super(name);
        minions = new HashMap<>();
    }

    @Override public String getName()
    {
        return this.name;
    }

    @Override public DecoratorMinion findMinion(String name)
    {
        DecoratorMinion found = null;
        Iterator<CompositeMinion> compIter;

        if(minions.get(name) != null)
        {
            // Minion is in this group
            found = minions.get(name).findMinion(name);
        }
        else
        {
            // Need to recurse
            compIter = minions.values().iterator();
            while(compIter.hasNext() && found != null)
            {
                found = compIter.next().findMinion(name);
            }
        }

        return found;
    }

    @Override public MinionGroup findGroup(String name)
    {
        MinionGroup found = null;
        Iterator<CompositeMinion> compIter;

        if(this.name.equals(name))
        {
            // Looking for this group
            found = this;
        }
        else
        {
            // Need to recurse
            compIter =  minions.values().iterator();
            while(compIter.hasNext() && found != null)
            {
                found = compIter.next().findGroup(name);
            }
        }

        return found;
    }

    @Override public void instructCreateEvilPlan()
    {
        for(CompositeMinion m : minions.values())
        {
            m.instructCreateEvilPlan();
        }
    }

    @Override public void instructConstructDeathRay()
    {
        for(CompositeMinion m : minions.values())
        {
            m.instructConstructDeathRay();
        }
    }
        
    public void addChild(DecoratorMinion m)
    {
        minions.put(m.getName(), m);
    }
}
```

DecoratorMinion.java
```java
public abstract class DecoratorMinion extends CompositeMinion
{
    public DecoratorMinion(String name)
    {
        super(name);
    }

    public String getName()
    {
        return name;
    }

    @Override public DecoratorMinion findMinion(String name)
    {
        DecoratorMinion found = null;

        if(this.name.equals(name))
        {
            found = this;
        }

        return found;
    }

    @Override public MinionGroup findGroup(String name)
    {
        return null;
    }

    @Override public void instructCreateEvilPlan()
    {
        EarpieceInstructor.instruct(this, createEvilPlan());
    }

    @Override
    public void instructConstructDeathRay()
    {
        EarpieceInstructor.instruct(this, constructDeathRay());
    }

    public abstract float createEvilPlan();

    public abstract float constructDeathRay();
}
```

ConcreteMinion.java
```java
public class ConcreteMinion extends DecoratorMinion
{
    public ConcreteMinion(String name)
    {
        super(name);
    }

    @Override public float createEvilPlan()
    {
        return 0.4f;
    }

    @Override public float constructDeathRay()
    {
        return 0.2f;
    }
}
```

Modfification.java
```java
public abstract class Modification extends DecoratorMinion
{
    DecoratorMinion next;

    public Modification(DecoratorMinion next)
    {
        super(next.name);
        this.next = next;
    }
}
```

BigBrainModification.java
```java
public class BigBrainModification extends Modification
{
    public BigBrainModification(DecoratorMinion next)
    {
        super(next);
    }

    @Override public float createEvilPlan()
    {
        return 0.5f + next.createEvilPlan();
    }

    @Override public float constructDeathRay()
    {
        return 0.1f + next.constructDeathRay();
    }
}
```

SuperStrengthModification.java
```java
public class SuperStrengthModification extends Modification
{
    public SuperStrengthModification(DecoratorMinion next)
    {
        super(next);
    }

    @Override public float createEvilPlan()
    {
        return next.createEvilPlan();
    }

    @Override public float constructDeathRay()
    {
        return 0.6f + next.constructDeathRay();
    }
}
```

CheerfulModification.java
```java
public class CheerfulModification extends Modification
{
    public CheerfulModification(DecoratorMinion next)
    {
        super(next);
    }

    @Override public float createEvilPlan()
    {
        System.out.printf(
            "%s: \"Oh boy do I love making evil plans!\"\n", name);
        return next.createEvilPlan();
    }

    @Override public float constructDeathRay()
    {
        System.out.printf(
            "%s: \"Wow, constructing death rays is my favourite!\"\n", name);
        return next.createEvilPlan();
    }
}
```