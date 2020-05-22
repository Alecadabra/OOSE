package View;

import java.util.List;

public interface UserInterface
{
    public void log(String message);

    public void showList(String title, List<String> list);

    public String inputFrom(String prompt, List<String> options);

    public String inputUnchecked(String prompt);

    public void showWelcome();

    public void showGoodbye();
}