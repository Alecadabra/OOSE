package IntMap;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args)
    {
        IntMapper map = new IntMapper();
        ArrayList<IntMapper.Pair> pairs = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
        {
            pairs.add(new IntMapper.Pair(i, Integer.toString(i)));
            // When i==3, add ("3",3) to list for 1 through 10
        }
        map.put(pairs, 42);

        System.out.println(map.get(pairs)); // Prints 42

    }
}