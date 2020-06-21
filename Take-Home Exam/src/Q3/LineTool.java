package Q3;

public class LineTool implements ToolState
{
    @Override
    public void draw(Canvas canvas, int x1, int y1, int x2, int y2)
    {
        canvas.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void fill(Canvas canvas, int x1, int y1, int x2, int y2)
    {
        canvas.drawLine(x1, y1, x2, y2);
    }
}