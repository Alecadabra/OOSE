package Q1;

import java.util.HashSet;
import java.util.Set;

public class PhysicsEngineSubject
{
    private Set<CollisionObserver> collObs = new HashSet<>();
    private Set<BonusItemObserver> bonusObs = new HashSet<>();

    public void addObserver(CollisionObserver ob)
    {
        collObs.add(ob);
    }

    public void addObserver(BonusItemObserver ob)
    {
        bonusObs.add(ob);
    }

    private void collision(GameObject obj1, GameObject obj2, float velocity)
    {
        if(obj1 instanceof PlayerCharacter && obj2 instanceof BonusItem)
        {
            notifyBonusItemObservers((PlayerCharacter)obj1, (BonusItem)obj2);
        }
        else if(obj2 instanceof PlayerCharacter && obj1 instanceof BonusItem)
        {
            notifyBonusItemObservers((PlayerCharacter)obj2, (BonusItem)obj1);
        }
        else
        {
            notifyCollisionObservers(obj1, obj2, velocity);
        }
    }

    private void notifyCollisionObservers(GameObject obj1, GameObject obj2, float velocity)
    {
        for(CollisionObserver ob : collObs)
        {
            ob.objectCollision(obj1, obj2, velocity);
        }
    }

    private void notifyBonusItemObservers(PlayerCharacter player, BonusItem item)
    {
        for(BonusItemObserver ob : bonusObs)
        {
            ob.itemUsed(player, item);
        }
    }
}