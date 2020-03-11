# Q1

## (a) 

`Mountain var = new Mountain();`

## (b)

`Mountain var = new Mountain();`

## (c)

`Mountain var = new Mountain();`
`GeographicalFeature var2 = var;`

## (d)

```java
public Nation createNation(Place x)
{
    return new Nation(x.getName())
}
```

## (e)

```java
public Place getBetterPlace(Place p)
{
    if(p instanceof Anarchy)
    {
        return new Republic();
    }
    else if(p instanceof Valley)
    {
        return new Volcano();
    }
    return null;
}
```

## (f)

```java
private boolean isInside(GeographicalFeature f, Nation y) {...}

public GeographicalFeature findInside(GeograpicalFeature thing, List<Nation> nationList)
{
    for(Nation nat : nationList)
    {
        if(isInside(thing, nat))
        {
            return thing;
        }
    }
    return null;
}
```

## (g)

```java
public void find(List<Place> list, int code)
{
    for(Place element : list)
    {
        if(test(element, code))
        {
            System.out.println(element.getName());
        }
    }
}

public boolean test(Place element, int code)
{
    return element.getPrefix() == code;
}
```

# Q2

