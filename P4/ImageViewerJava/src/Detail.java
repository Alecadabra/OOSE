/**
 * Decorator pattern implementation to add details to an image
 */
public abstract class Detail implements ImageFile
{
    protected ImageFile next;

    public Detail(ImageFile next)
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