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