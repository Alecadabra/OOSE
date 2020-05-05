package JourneyPlanner.PackagingMaterial;

public class CardboardBox implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.6f;
    }
}