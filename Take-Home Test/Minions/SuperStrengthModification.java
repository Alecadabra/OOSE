package Minions;

public class SuperStrengthModification extends Modification
{
    public SuperStrengthModification(DecoratorMinion next)
    {
        super(next);
    }

    @Override public float createEvilPlan()
    {
        return next.createEvilPlan();
    }

    @Override public float constructDeathRay()
    {
        return 0.6f + next.constructDeathRay();
    }
}