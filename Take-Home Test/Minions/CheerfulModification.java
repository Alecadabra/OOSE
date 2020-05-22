package Minions;

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