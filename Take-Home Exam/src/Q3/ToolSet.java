package Q3;

import java.util.Arrays;
import java.util.List;

public class ToolSet
{
    private Canvas canvas;
    private List<Class<? extends ToolState>> toolList;
    private int currToolIdx;
    private ToolState tool;

    public ToolSet()
    {
        this.canvas = new Canvas();
        this.toolList = Arrays.asList(
            LineTool.class, RectangleTool.class, CircleTool.class);
        this.currToolIdx = 0;
        this.tool = getTool();
    }

    public void draw(int x1, int y1, int x2, int y2)
    {
        tool.draw(canvas, x1, y1, x2, y2);
    }

    public void fill(int x1, int y1, int x2, int y2)
    {
        tool.fill(canvas, x1, y1, x2, y2);
    }

    public void nextTool()
    {
        if(currToolIdx == toolList.size() - 1)
        {
            currToolIdx = 0;
        }
        else
        {
            currToolIdx += 1;
        }

        this.tool = getTool();
    }

    public void prevTool()
    {
        if(currToolIdx == 0)
        {
            currToolIdx = toolList.size() - 1;
        }
        else
        {
            currToolIdx -= 1;
        }

        this.tool = getTool();
    }

    private ToolState getTool()
    {
        try
        {
            return toolList.get(currToolIdx).getConstructor()
                .newInstance(new Object[0]);
        }
        catch(ReflectiveOperationException e)
        {
            return null;
        }
    }
}