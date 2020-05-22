public class RatingDetail extends Detail
{
    private int rating;

    public RatingDetail(int rating, ImageFile next)
    {
        super(next);
        this.rating = rating;
    }

    @Override public String getDetails()
    {
        return next.getDetails() + "; Rating: " + rating;
    }
}