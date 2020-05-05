package IntMap;

import java.util.List;
import java.util.HashMap;

public class IntMapper
{
    public static class Pair
    {
        private Object obj1;
        private Object obj2;

        public Pair(Object obj1, Object obj2)
        {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        public Object get1()
        {
            return obj1;
        }

        public Object get2()
        {
            return obj2;
        }
    }

    private HashMap<List<Pair>, Integer> map;

    public IntMapper()
    {
        map = new HashMap<>();
    }

    public void put(List<Pair> pair, int num)
    {
        map.put(pair, num);
    }

    public int get(List<Pair> pair)
    {
        return map.get(pair);
    }
}