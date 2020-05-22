package JourneyPlanner.TransportMethod;

public class Plane implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        return 15 + distance / 3;
    }
}