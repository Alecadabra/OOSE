public class DateDetail extends Detail
{
    private String date;

    protected DateDetail(String date, Image next)
    {
        super(next);
        this.date = date;
    }

    @Override public String getDetails()
    {
        return next.getDetails() + "; Date: " + date;
    }
}