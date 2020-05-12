# Worksheet 5: Event-Driven Programming

## Question 1 - Discussion

### (a)

Standard Observer pattern with two concrete Observers Y & Z with the Subject X.

### (b)

Obserer pattern with concrete observer X and two Subjects Y and Z.

### (c)

3 Concrete observers X, Y and Z, 3 Subjects I, J and K.

### (d)

Concrete Observer Y with multiple inheritance on 3 observer interfaces with one
Subject X storing one to many of each of them.

### (e)

Concrete Observer/Subject Y is an Observer1 and a subject of Observer2, and vice
versa for Observer/Subject X.

### (f)

W is a subject of Observer1, who has concrete observers X and Z, X also being a
Subject of Observer2 while Z is a concrete Observer for Observer2. Y is also a
concrete Observer of Observer2.

## Question 2 - Theoretical Design

### (a)

![UML](https://i.imgur.com/AXitgID.png)

### (b)

The Observer pattern is useful here because we havea controller that must react
in multiple unique and complicated ways to new information being presented to
it. Without the observer pattern, the approach would be less scalable and result
in the controller class (Satellite) having to know/deal with the view
operations with less cohesion.

### (c)

```java
public class Satellite
{
    List<Observer> obs;
    double lat;
    double lon;

    public Satellite()
    {
        obs = new ArrayList<>();
        lat = 0;
        lon = 0;

        obs.add(new SatelliteLogger());
        obs.add(new SatelliteEmailer());
        obs.add(new SatelliteAlert());
    }

    ...
}

public interface Observer
{
    public void setup();
    public void newFormation();
    public void newDissapearance();
}

public class XObserver implements Observer
{
    private Satellite sat; // Back reference to subject

    public XObserver() { ... }

    @Override
    public void setup()
    {
        sat.addObserver(this);
    }

    @Override
    public void newFormation()
    {
        double lat = sat.getLat;
        double lon = sat.getLon;

        ...
    }

    @Override
    public void newDissapearance() { ... }
}
```

## Question 3 - Implementing the Observer Pattern

See Reminder directory