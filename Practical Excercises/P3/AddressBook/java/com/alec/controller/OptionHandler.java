package controller;

import java.util.ArrayList;

public class OptionHandler
{
    private ArrayList<Option> options;

    public OptionHandler()
    {
        options = new ArrayList<>();
    }

    public int addOption(Option inOption)
    {
        int idx = options.size();
            // Index this option will be inserted at

        options.add(inOption);

        return idx;
    }

    public String doOption(int idx, String input)
    {
        String result;

        try
        {
            result = options.get(idx).doOption(input);
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException(
                "Option index " + idx + " out of bounds", e);
        }

        return result;
    }
}