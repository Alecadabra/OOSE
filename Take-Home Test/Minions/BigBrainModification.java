package Minions;

public class BigBrainModification extends Modification
{
    public BigBrainModification(DecoratorMinion next)
    {
        super(next);
    }

    @Override public float createEvilPlan()
    {
        return 0.5f + next.createEvilPlan();
    }

    @Override public float constructDeathRay()
    {
        return 0.1f + next.constructDeathRay();
    }
}