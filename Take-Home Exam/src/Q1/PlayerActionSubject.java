package Q1;

import java.util.HashSet;
import java.util.Set;

public class PlayerActionSubject
{
    private Set<BonusItemObserver> bonusObs = new HashSet<>();
    private PlayerCharacter player;

    public void addObserver(BonusItemObserver ob)
    {
        bonusObs.add(ob);
    }

    public void useBonusItemFromInventory(BonusItem item)
    {
        notifyObservers(item);
    }

    private void notifyObservers(BonusItem item)
    {
        for(BonusItemObserver ob : bonusObs)
        {
            ob.itemUsed(this.player, item);
        }
    }
}