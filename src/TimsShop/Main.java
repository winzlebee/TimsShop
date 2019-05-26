
package TimsShop;
import TimsShop.Controllers.ApplicationController;
import javafx.application.Application;
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
    * test edit
********************************************************************/
public class Main extends Application  // Application class serves as the Entry point to a FX program.
{
     /**********************************************************************
     Function: loads XML document and instantiates the main Stage (view) 
     Arguments: the initial frame of the program
     Result: Sets the frame to show.
      ************************************************************************/
    @Override
    public void start(Stage primaryStage) 
    {         
        ApplicationController.getInstance().startApplication();
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
