package Q1;

public interface BonusItemObserver
{
    public void itemUsed(PlayerCharacter player, BonusItem item);

	public void itemExpired(PlayerCharacter player, BonusItem item);
}