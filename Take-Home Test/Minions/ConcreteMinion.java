package Minions;

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