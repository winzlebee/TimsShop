
package TimsShop.Controllers;
import java.io.IOException;
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
public class ApplicationController extends Application implements AbstractController // Application class serves as the Entry point to a FX program.
{
     /**********************************************************************
     Function: loads XML document and instantiates the main Stage (view) 
     Arguments: the initial frame of the program
     Result: Sets the frame to show.
     * @throws java.lang.Exception
      ************************************************************************/
    
    private Stage nextStage;
    private Parent source;
    private LoginController loginController = LoginController.getInstance();
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {   
        nextStage = primaryStage;
        openNext(); 
        passControl(loginController);
    }
    
    @Override
    public void openNext() throws IOException 
    {
        //Load xml document
        source = FXMLLoader.load(getClass().getResource("/TimsShop/FXML/EmployeeLoginView.fxml"));
        Scene scene = new Scene(source);
        nextStage.setScene(scene);
        nextStage.show(); 
    }


    @Override
    public void passControl(AbstractController nextController) 
    {
        nextController.setCurrentStage(nextStage);
    }
    
   //could add splash state/loading screen?
    public void closeView(){}
    public void setCurrentStage(Stage nextStage) { }
    public void setNextSource(Parent nextStage) {}
    


    
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
