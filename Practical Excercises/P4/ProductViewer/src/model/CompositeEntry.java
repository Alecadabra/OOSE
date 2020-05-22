package model;

public abstract class CompositeEntry implements Comparable<CompositeEntry>
{
    protected String name;

    public CompositeEntry(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void addProduct(CompositeEntry p)
    {
        throw new UnsupportedOperationException(
            "Cannot add product to this type");
    }

    @Override public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override public int compareTo(CompositeEntry group)
    {
        return name.compareTo(group.name);
    }

    @Override public abstract boolean equals(Object obj);
}