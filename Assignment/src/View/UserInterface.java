package View;

import java.util.List;
import java.util.Set;

public interface UserInterface
{
    public void log(String message);

    public void showList(String title, List<String> list);

    public String selectFromSet(String prompt, Set<String> set);
}