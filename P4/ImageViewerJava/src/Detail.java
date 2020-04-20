public abstract class Detail implements Image
{
    protected Image next;

    public Detail(Image next)
    {
        this.next = next;
    }

    public String getFileName()
    {
        return next.getFileName();
    }

    public String getDetails()
    {
        return next.getDetails();
    }
}