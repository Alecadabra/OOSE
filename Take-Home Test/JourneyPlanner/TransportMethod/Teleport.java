package JourneyPlanner.TransportMethod;

public class Teleport implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        // Distance doesn't matter with teleporting!
        return 1;
    }
}