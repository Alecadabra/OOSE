package view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;

/** 
 * A dialog box allowing the user to enter details (i.e. the name) of a new
 * product group to create.
 */
public class AddGroupDialog extends Dialog<ButtonType>
{
    private static final ButtonType ADD_BUTTON = 
        new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    
    private Label parentLabel = new Label("-");
    private TextField nameField = new TextField();

    public AddGroupDialog() 
    {
        setTitle("Add New Product Group (Product Viewer)");
        setHeaderText("Enter new product group details, and press \"Add\".\n" 
            + "(Note that the parent group has no effect at the moment.)");
    
        DialogPane pane = getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.CANCEL, ADD_BUTTON);
        
        GridPane grid = new GridPane();
        grid.add(new Label("Parent Group:"), 1, 1);
        grid.add(parentLabel, 2, 1);
        grid.add(new Label("Name:"), 1, 2);
        grid.add(nameField, 2, 2);
        grid.setHgap(5.0);
        grid.setVgap(5.0);
        pane.setContent(grid);        
    }
    
    public String getName()
    {
        return nameField.getText();
    }
    
    /**
     * Displays the dialog box, waits for the user to enter all necessary 
     * details, and returns 'true' iff the user presses the "Add" button.
     */
    public boolean showDialog(String parentGroup)
    {
        setResult(null);
        parentLabel.setText(parentGroup);
        
        Optional<ButtonType> button = showAndWait();
        return button.isPresent() && button.get() == ADD_BUTTON;
    }
}
