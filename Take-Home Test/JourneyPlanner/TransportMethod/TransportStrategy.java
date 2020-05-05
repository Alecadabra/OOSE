package JourneyPlanner.TransportMethod;

/**
 * Strategy pattern interface for a transport method
 */
public interface TransportStrategy
{
    public int timeTaken(int distance);
}