package JourneyPlanner;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import JourneyPlanner.PackagingMaterial.PackagingStrategy;
import JourneyPlanner.TransportMethod.TransportStrategy;

/**
 * Represents a journey taken by a package over a given distance, using a
 * specific packaging material and over 1 or more transport methods
 */
public class Journey
{
    private int distance;
    private PackagingStrategy packaging;
    private LinkedList<TransportStrategy> transportMethods;

    /**
     * Constructor with distance field
     * @param distance Distance of journey, kilometres
     * @param packaging Packaging material of type PackagingStrategy
     * @param transportStrategies Varargs of transport strategies to use, in 
     * order
     */
    public Journey(int distance, PackagingStrategy packaging,
        TransportStrategy... transportStrategies)
    {
        this.distance = distance;
        this.packaging = packaging;
        this.transportMethods = new LinkedList<>();
        for(TransportStrategy strat : transportStrategies)
        {
            this.transportMethods.addLast(strat);
        }
    }

    /**
     * Alternate constructor without distance field
     * @param packaging Packaging material of type PackagingStrategy
     * @param transportStrategies Varargs of transport strategies to use, in 
     * order
     */
    public Journey(PackagingStrategy packaging,
        TransportStrategy... transportStrategies)
    {
        this.distance = 0;
        this.packaging = packaging;
        this.transportMethods = new LinkedList<>();
        for(TransportStrategy strat : transportStrategies)
        {
            this.transportMethods.addLast(strat);
        }
    }

    /**
     * Gets the distance for the journey in kilometres
     * @return Distance: int
     * @throws NoSuchElementException If no distance specified
     */
    public int getDistance() throws NoSuchElementException
    {
        if(this.distance == 0)
        {
            throw new NoSuchElementException("No distance specified");
        }

        return this.distance;
    }
    
    /**
     * Get estimated time taken to complete journey in hours using transport
     * strategys over the distance
     * @return time: int
     * @throws NoSuchElementException If no distance specified
     */
    public int timeTaken() throws NoSuchElementException
    {
        int time = 0;

        if(this.distance == 0)
        {
            throw new NoSuchElementException("No distance specified");
        }

        for(TransportStrategy strat : this.transportMethods)
        {
            time += strat.timeTaken(this.distance);
        }

        return time;
    }

    /**
     * Get estimated proportion of package remaining using the packaging
     * material from 0-1
     * @return proportion: float
     */
    public float proportionRemaining()
    {
        return this.packaging.proportionRemaining();
    }
}