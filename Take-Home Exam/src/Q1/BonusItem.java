package Q1;

import java.util.Set;

public abstract class BonusItem extends GameObject
{
	BonusItemTimer timer = new BonusItemTimer(this);
	Set<BonusItemObserver> obs;
	PlayerCharacter player;

	public void startTimer(PlayerCharacter player, BonusItem item, BonusItemObserver callback)
	{
		this.player = player;

		if(!obs.contains(callback))
		{
			obs.add(callback);
		}

		timer.start();
	}

	public void timerFinished()
	{
		for(BonusItemObserver ob : obs)
		{
			ob.itemExpired(this.player, this);
		}
	}

	public void removeFromGame()
	{
	}
}
