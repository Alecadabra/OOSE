package JourneyPlanner.PackagingMaterial;

public class UnPackaged implements PackagingStrategy
{
    @Override public float proportionRemaining()
    {
        return 0.2f;
    }
}