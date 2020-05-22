package JourneyPlanner.PackagingMaterial;

public class BubbleWrap implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.9f;
    }    
}