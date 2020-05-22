package Minions;

public abstract class Modification extends DecoratorMinion
{
    DecoratorMinion next;

    public Modification(DecoratorMinion next)
    {
        super(next.name);
        this.next = next;
    }
}