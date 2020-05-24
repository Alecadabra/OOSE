package View;

import java.util.List;

public interface UserInterface
{
    public void heading(String heading);
    
    public void display(String content);

    public void log(String message);

    public void showList(String title, List<?> list);

    public String inputFrom(String prompt, List<String> options);

    public String inputUnchecked(String prompt);
}