
package TimsShop.Controllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/****************************************************************
This class serves as controller between the views of  Tims Toy shop.
NOte: Any @FXML annotations denote XML loaded components/listeners
    1 Constructs an instance of the specified Application class
    2 Calls the init() method
    3 Calls the start(javafx.stage.Stage) method
    4 Waits for the application to finish, which happens when either of the following occur:
        * the application calls Platform.exit()
        * the last window has been closed and the implicitExit attribute on Platform is true
    5 Calls the stop() method
    * 
    * 
********************************************************************/
public class MainController extends Application  // Application class serves as the Entry point to a FX program.
{
     /**********************************************************************
     Function: loads XML document and instantiates the main Stage (view) 
     Arguments: the initial frame of the program
     Result: Sets the frame to show.
      ************************************************************************/
    @Override
    public void start(Stage primaryStage) throws Exception 
    {   //Load xml document
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }  
}
