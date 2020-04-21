import model.*;
import view.MainWindow;
import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

/**
 * Main class for the product viewer application.
 */
public class ProductViewer extends Application
{
    public static final String DEFAULT_CATALOGUE_FILE = "catalogue.txt";

    public static void main(String[] args)
    {
        if(args.length > 1)
        {
            System.out.println("At most one argument expected");
            System.exit(1);
        }
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage)
    {
        // We'll either read 'catalogue.txt' or whatever is specified on the 
        // command-line.
        List<String> params = getParameters().getRaw();
        String catalogueFile = DEFAULT_CATALOGUE_FILE;
        if(params.size() == 1)
        {
            catalogueFile = params.get(0);
        }
    
        // Create the model and controller.
        Catalogue cat = new Catalogue();
        Controller control = new Controller(cat); 
        try
        {
            // Populate the model.
            cat.readFrom(catalogueFile);
            
            // Create the user interface and begin.
            MainWindow window = new MainWindow(cat, control, stage);
            stage.show();
        }
        catch(CatalogueException e)
        {
            System.err.printf("Error while reading '%s': '%s'\n", catalogueFile, e.getMessage());
            System.exit(1);
        }
    }
}
