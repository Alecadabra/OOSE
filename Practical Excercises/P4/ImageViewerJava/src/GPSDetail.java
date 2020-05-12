public class GPSDetail extends Detail
{
    private double x;
    private double y;
    private double z;

    public GPSDetail(double x, double y, double z, ImageFile next)
    {
        super(next);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override public String getDetails()
    {
        return next.getDetails() + "; GPS: " + x + ", " + y + ", " + z;
    }
} 