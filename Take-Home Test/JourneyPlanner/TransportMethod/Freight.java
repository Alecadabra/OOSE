package JourneyPlanner.TransportMethod;

public class Freight implements TransportStrategy
{
    @Override public int timeTaken(int distance)
    {
        return 12 + distance * 2;
    }
}