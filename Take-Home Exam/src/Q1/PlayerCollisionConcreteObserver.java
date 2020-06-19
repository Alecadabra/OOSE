package Q1;

public class PlayerCollisionConcreteObserver implements CollisionObserver
{
    @Override
    public void objectCollision(GameObject obj1, GameObject obj2, float velocity)
    {
        PlayerCharacter player = null;

        if(velocity > 10 && (obj1 instanceof PlayerCharacter || obj2 instanceof PlayerCharacter))
        {
            if(obj1 instanceof PlayerCharacter)
            {
                player = (PlayerCharacter)obj1;
            }
            else if(obj2 instanceof PlayerCharacter)
            {
                player = (PlayerCharacter)obj2;
            }

            player.takeDamage((int)velocity);
        }
    }    
}