import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;

/**
 * Represents the main user interface for the Image Viewer application.
 */
public class MainWindow
{
    private Album album;
    private Stage stage;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
    
    public MainWindow(Album album, Stage stage)
    {
        this.album = album;
        this.stage = stage;
    }
    
    public File chooseAlbumFile()
    {
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Select Album File");
        dialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        File currentDir = new File(System.getProperty("user.dir"));
        File resourcesDir = new File(currentDir, "resources");        
        if(resourcesDir.isDirectory())
        {
            dialog.setInitialDirectory(resourcesDir);
        }
        else
        {
            dialog.setInitialDirectory(currentDir);
        }
        return dialog.showOpenDialog(stage);
    }
    
    /**
     * Builds the main UI, based on an Album and a JavaFX 'Stage' (which is 
     * basically a pre-existing window).
     */
    public void show()
    {
        Platform.setImplicitExit(true);
        stage.setTitle("JavaFX Image Viewer");
        
        String url = new File(album.getFileName()).toURI().toString();
        imageWidget.setImage(new ImageRecord(url));
        
        // Add 'mainBox' to the window. This is a container for holding the
        // other bits: the toolbar, scroller (containing the image), and
        // caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and
        // similarly for prevBtn.
        // (This uses Java 8's 'method reference' feature.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        captionWidget.setText(album.getInfo());
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
        stage.show();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        album.prev();
        String url = new File(album.getFileName()).toURI().toString();
        imageWidget.setImage(new ImageRecord(url));
        captionWidget.setText(album.getInfo());
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        album.next();
        String url = new File(album.getFileName()).toURI().toString();
        imageWidget.setImage(new ImageRecord(url));
        captionWidget.setText(album.getInfo());
    }
}
