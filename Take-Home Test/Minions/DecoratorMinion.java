package Minions;

public abstract class DecoratorMinion extends CompositeMinion
{
    public DecoratorMinion(String name)
    {
        super(name);
    }

    public String getName()
    {
        return name;
    }

    @Override public DecoratorMinion findMinion(String name)
    {
        DecoratorMinion found = null;

        if(this.name.equals(name))
        {
            found = this;
        }

        return found;
    }

    @Override public MinionGroup findGroup(String name)
    {
        return null;
    }

    @Override public void instructCreateEvilPlan()
    {
        EarpieceInstructor.instruct(this, createEvilPlan());
    }

    @Override
    public void instructConstructDeathRay()
    {
        EarpieceInstructor.instruct(this, constructDeathRay());
    }

    public abstract float createEvilPlan();

    public abstract float constructDeathRay();
}