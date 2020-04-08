# Worksheet 4: Object Relationships

## Q1

> Consider each of the following class structures, and explain what kind of object structures could result from it

### (a)

Recursive aggregation.

Contains zero to many of itself. An n-ary tree (0..1 is a linked list, 0..2 is a binary tree).

### (b)

A-B-A-B-A-B. Linked list with alternating object types.

### (c)

Alternating binary tree.

### (d)

Assume A is abstract/interface. B and C implement A, which aggregates C.

Root is B or C, then a binary tree of C's.

### (e)

Can basically ignore A for the purpose of this UML.

This is a class (B) containing 0 to many objects of class C.

### (f)

This is decorator pattern in the left, B is the decorator. Composite pattern on the right, D is the composite branch. Class C is the leaves of the entire pattern.

## Q2

### (a) (i)

Leave for workshop