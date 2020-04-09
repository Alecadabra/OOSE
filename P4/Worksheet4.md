# Worksheet 4: Object Relationships

# Q1

> Consider each of the following class structures, and explain what kind of object structures could result from it

## (a)

Recursive aggregation.

Contains zero to many of itself. An n-ary tree (0..1 is a linked list, 0..2 is a binary tree).

## (b)

A-B-A-B-A-B. Linked list with alternating object types.

## (c)

Alternating binary tree.

## (d)

Assume A is abstract/interface. B and C implement A, which aggregates C.

Root is B or C, then a binary tree of C's.

## (e)

Can basically ignore A for the purpose of this UML.

This is a class (B) containing 0 to many objects of class C.

## (f)

This is decorator pattern in the left, B is the decorator. Composite pattern on the right, D is the composite branch. Class C is the leaves of the entire pattern.

# Q2

> One of the lecture examples uses the Composite Pattern to represent files and directories, and shows how to find a given named file within the structure. However, it is somewhat limited.

## (a)

### (i)

A hash table/map/dict/assoc arr would be much faster.

### (ii)

```java
public class Directory implements Entry
{
    private HashMap<Entry> children;
    ...
    @Override public FileEntry find(String name)
    {
        FileEntry found = children.get(name).find(name);
            // Recurse

        if(found != null) return found;
        else              return null;
    }
    ...
}
```

## (b)

Have the map store a list of entries and return the list

## (c)

Something