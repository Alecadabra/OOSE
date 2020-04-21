package view;
import controller.*;
import model.*;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;

import java.util.*;

/**
 * Represents the main user interface for the Product Viewer application. This
 * shows a toolbar across the top, including a combo box showing available 
 * product groups, and the contents of the current group in the main table.
 */
public class MainWindow
{
    private Catalogue catalogue;
    private Controller control;
    private String currentGroup = "";
    
    private ComboBox<String> groupList = new ComboBox<>();
    private TableView<Product> productTable = new TableView<>();
    private Label valueLabel = new Label();
    
    private AddGroupDialog addGroupDialog = new AddGroupDialog();
    private AddProductDialog addProductDialog = new AddProductDialog();
    private Alert alertDialog = new Alert(Alert.AlertType.ERROR);
    
    /** 
     * Convenience method for retrieving an image from within the running .jar
     * file, given the image's filename.
     */
    private static ImageView getIcon(String file)
    {
        return new ImageView(new Image(
            MainWindow.class.getResource("/" + file).toString()));
    }
    
    /**
     * Constructs the UI object.
     */
    public MainWindow(Catalogue catalogue, Controller control, Stage stage)
    {
        this.catalogue = catalogue;
        this.control = control;
        
        stage.setTitle("JavaFX Product Viewer");
        stage.setMinWidth(600);

        // Create our toolbar buttons
        Button upGroupBtn = new Button("Up", getIcon("angle-up.png"));
        Button addProductBtn = new Button("Add Product", getIcon("plus.png"));
        Button addProductGroupBtn = new Button("Add Product Group", getIcon("folder-plus.png"));
        ToolBar toolBar = new ToolBar(groupList, upGroupBtn, addProductBtn, addProductGroupBtn);
        
        // Be pedantic about maintaining a consistent height for toolbar things.
        groupList.setMaxHeight(Double.MAX_VALUE);
        upGroupBtn.setMaxHeight(Double.MAX_VALUE);
        addProductBtn.setMaxHeight(Double.MAX_VALUE);
        addProductGroupBtn.setMaxHeight(Double.MAX_VALUE);
        
        // Set up event handlers (what to do when a product group is selected 
        // or a button is pressed).
        groupList.setOnAction((event) -> updateProducts());
        upGroupBtn.setOnAction(this::upGroupHandler);
        addProductBtn.setOnAction(this::addProductHandler);
        addProductGroupBtn.setOnAction(this::addProductGroupHandler);
        
        TableColumn<Product,String> nameCol = new TableColumn<>("Product Name");
        TableColumn<Product,String> priceCol = new TableColumn<>("Price");
        TableColumn<Product,Integer> numberCol = new TableColumn<>("Number in Stock");
        
        // The following tell JavaFX how to extract information from a Product 
        // object and put it into the three table columns.
        nameCol.setCellValueFactory(   
            (cell) -> new SimpleStringProperty(cell.getValue().getName()) );
            
        priceCol.setCellValueFactory(  
            (cell) -> new SimpleStringProperty(
                String.format("$%,.2f", cell.getValue().getPrice())) );
            
        numberCol.setCellValueFactory( 
            (cell) -> new SimpleIntegerProperty(
                cell.getValue().getNumberInStock()).asObject() );
          
        // Set and adjust table column widths (making the first wider).
        nameCol.prefWidthProperty().bind(productTable.widthProperty().divide(2));
        priceCol.prefWidthProperty().bind(productTable.widthProperty().divide(4));
        numberCol.prefWidthProperty().bind(productTable.widthProperty().divide(4));            
        
        // Add the columns to the table. (We could legitimately do 'addAll', 
        // but I want to avoid the warning message that goes with the
        // combination of generics and varargs.)
        productTable.getColumns().add(nameCol);
        productTable.getColumns().add(priceCol);
        productTable.getColumns().add(numberCol);

        // Add the main parts of the UI to the window.
        BorderPane mainBox = new BorderPane();
        mainBox.setTop(toolBar);
        mainBox.setCenter(productTable);
        mainBox.setBottom(valueLabel);
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.sizeToScene();
        
        updateGroups();
        updateProducts();
    }
    
    /** 
     * Re-populates the list of groups, and ensures the current group doesn't 
     * change unless it's no longer in the list. This must be called after any 
     * operation that modifies the set of product groups to be displayed.
     */
    private void updateGroups()
    {
        List<String> newItems = new ArrayList<>();
        
        // TODO: getProductGroupSet() returns the set of all groups. However,
        // we *only* want to include the current group and its sub-groups.
        for(ProductGroup group : catalogue.getProductGroupSet())
        {
            newItems.add(group.getName());
        }
        groupList.getItems().setAll(newItems);
        
        if(newItems.isEmpty())
        {
            currentGroup = "";
        }
        else 
        {
            if(!newItems.contains(currentGroup))
            {
                currentGroup = newItems.get(0);
            }
            groupList.setValue(currentGroup);
        }
    }
    
    /** 
     * Re-populates the list of products. This must be called after any 
     * operation that modifies the set of products to be displayed.
     */
    private void updateProducts()
    {
        currentGroup = groupList.getValue();
        
        ProductGroup group = catalogue.getProductGroup(currentGroup);
        if(group != null)
        {
            // TODO: getProducts() returns the products in one group. However,
            // we want the to include products in its *sub-groups* as well.
            
            productTable.getItems().setAll(group.getProducts());
        }
        else
        {
            productTable.getItems().clear();
        }
        valueLabel.setText(String.format(
            "Total value: $%,.2f", control.calcValue(currentGroup)));
    }
    
    /**
     * Change to the parent of the current product group (unless we're already
     * at the top of the tree).
     */
    private void upGroupHandler(ActionEvent event)
    {
        // TODO: add code here to change 'currentGroup' up one level (to the 
        // group owning it), unless it's already the very top-level group.
        
        // ...
        
        updateGroups();
        // groupList.setValue(parentGroupName); // Set the current group
        updateProducts();
    }

    /**
     * Displays a dialog box for adding a new product, and coordinates with
     * the controller to add it.
     */
    private void addProductHandler(ActionEvent event)
    {
        boolean tryAgain;
        do
        {
            tryAgain = false;
            if(addProductDialog.showDialog(currentGroup))
            {
                try
                {
                    control.addProduct(
                        addProductDialog.getName(),
                        currentGroup,
                        addProductDialog.getPrice(),
                        addProductDialog.getNumberInStock());
                        
                    updateProducts();
                }
                catch(ControllerException e)
                {
                    alertDialog.setContentText(e.getMessage());
                    alertDialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alertDialog.showAndWait();
                    tryAgain = true;
                }
            }
        }
        while(tryAgain);
    }

    /**
     * Displays a dialog box for adding a new product group, and coordinates 
     * with the controller to add it.
     */
    private void addProductGroupHandler(ActionEvent event)
    {
        boolean tryAgain;
        do
        {
            tryAgain = false;
            if(addGroupDialog.showDialog(currentGroup))
            {
                try
                {
                    String groupName = addGroupDialog.getName();
                    control.addProductGroup(groupName, currentGroup);
                    updateGroups();
                    groupList.setValue(groupName);
                    updateProducts();
                }
                catch(ControllerException e)
                {
                    alertDialog.setContentText(e.getMessage());
                    alertDialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alertDialog.showAndWait();
                    tryAgain = true;
                }
            }
        }
        while(tryAgain);
    }
}
