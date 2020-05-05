package Minions;

import java.util.HashMap;
import java.util.Iterator;

public class MinionGroup extends CompositeMinion
{
    HashMap<String, CompositeMinion> minions;

    public MinionGroup(String name)
    {
        super(name);
        minions = new HashMap<>();
    }

    @Override public String getName()
    {
        return this.name;
    }

    @Override public DecoratorMinion findMinion(String name)
    {
        DecoratorMinion found = null;
        Iterator<CompositeMinion> compIter;

        if(minions.get(name) != null)
        {
            // Minion is in this group
            found = minions.get(name).findMinion(name);
        }
        else
        {
            // Need to recurse
            compIter = minions.values().iterator();
            while(compIter.hasNext() && found != null)
            {
                found = compIter.next().findMinion(name);
            }
        }

        return found;
    }

    @Override public MinionGroup findGroup(String name)
    {
        MinionGroup found = null;
        Iterator<CompositeMinion> compIter;

        if(this.name.equals(name))
        {
            // Looking for this group
            found = this;
        }
        else
        {
            // Need to recurse
            compIter =  minions.values().iterator();
            while(compIter.hasNext() && found != null)
            {
                found = compIter.next().findGroup(name);
            }
        }

        return found;
    }

    @Override public void instructCreateEvilPlan()
    {
        for(CompositeMinion m : minions.values())
        {
            m.instructCreateEvilPlan();
        }
    }

    @Override public void instructConstructDeathRay()
    {
        for(CompositeMinion m : minions.values())
        {
            m.instructConstructDeathRay();
        }
    }
        
    public void addChild(DecoratorMinion m)
    {
        minions.put(m.getName(), m);
    }
}