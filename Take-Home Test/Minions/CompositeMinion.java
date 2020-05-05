package Minions;

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

    public abstract void instructCreateEvilPlan();

    public abstract void instructConstructDeathRay();
}