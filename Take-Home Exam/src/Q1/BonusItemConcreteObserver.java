package Q1;

public class BonusItemConcreteObserver implements BonusItemObserver
{
    @Override
    public void itemUsed(PlayerCharacter player, BonusItem item)
    {
        player.useItem(item);
        item.startTimer(player, item, this);
        item.removeFromGame();
    }

    @Override
    public void itemExpired(PlayerCharacter player, BonusItem item)
    {
        player.itemExpired(item);
    }
}