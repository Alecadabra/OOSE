package view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;
import java.util.Collection;

/** 
 * A dialog box allowing the user to enter details of a new product to create.
 */
public class AddProductDialog extends Dialog<ButtonType>
{
    private static final ButtonType ADD_BUTTON = 
        new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    
    private Label groupLabel = new Label();
    private TextField nameField = new TextField();
    private Spinner<Double> priceField = new Spinner<>(0, Double.MAX_VALUE, 0.0, 10.0);
    private Spinner<Integer> numberField = new Spinner<>(0, Integer.MAX_VALUE, 0, 1);

    public AddProductDialog() 
    {
        setTitle("Add New Product (Product Viewer)");
        setHeaderText("Enter new product details, and press \"Add\".");
    
        DialogPane pane = getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.CANCEL, ADD_BUTTON);
        
        GridPane grid = new GridPane();
        grid.add(new Label("Group:"), 1, 1);
        grid.add(groupLabel, 2, 1);
        grid.add(new Label("Name:"), 1, 2);
        grid.add(nameField, 2, 2);
        grid.add(new Label("Price:"), 1, 3);
        grid.add(priceField, 2, 3);
        grid.add(new Label("Number in stock:"), 1, 4);
        grid.add(numberField, 2, 4);
        grid.setHgap(5.0);
        grid.setVgap(5.0);
        pane.setContent(grid);        
        
        priceField.setEditable(true);
        numberField.setEditable(true);
        
        // The following are not hugely important for this exercise, but are 
        // here to fix a non-intuitive aspect of JavaFX -- that if the user 
        // manually types a value into a "spinner" input field (without using 
        // the up/down buttons), the entered value is completely ignored until
        // they actually do press up/down.
        
        priceField.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                try
                {
                    // First, check that the value entered is valid.
                    Double.parseDouble(priceField.getEditor().getText());
                    
                    // Second, force the spinner to commit the new value.
                    priceField.increment(0);
                }
                catch(NumberFormatException e)
                {
                    // If invalid, revert the text shown to the original value.
                    priceField.getEditor().setText(priceField.getValue().toString());
                }
            }
        });
        
        numberField.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                try
                {
                    // As above.
                    Integer.parseInt(numberField.getEditor().getText()); 
                    numberField.increment(0); 
                }
                catch(NumberFormatException e)
                {
                    numberField.getEditor().setText(numberField.getValue().toString());
                }
            }
        });
    }
    
    public String getName()
    {
        return nameField.getText();
    }
    
    public float getPrice()
    {
        return priceField.getValue().floatValue();
    }
    
    public int getNumberInStock()
    {
        return numberField.getValue();
    }
    
    /**
     * Displays the dialog box, waits for the user to enter all necessary 
     * details, and returns 'true' iff the user presses the "Add" button.
     */
    public boolean showDialog(String currentGroup)
    {
        setResult(null);
        groupLabel.setText(currentGroup);
        
        Optional<ButtonType> button = showAndWait();
        return button.isPresent() && button.get() == ADD_BUTTON;
    }
}
