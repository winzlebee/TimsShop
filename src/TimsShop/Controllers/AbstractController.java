
package TimsShop.Controllers;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;


public interface AbstractController 
{
    /************************************
        Abstract Controller generalzies functionality between the Controller
        Classes that will be present in the system
    *************************************/
    
    
    /*
     * Closes the CURRENT view through closing the stage
     */
    public void closeView() ;
    
    /*
     * Opens the next view (based upon what the View has told
        the controller, through the event listener).
     */
    public void openNext() throws IOException;
      
    /*
        * passes control to the next appropraite controller 
        @param The next controller to assume control over the system(or part of the system)
     */
    public void passControl(AbstractController nextController);
    
      /*
        * Sets what view is being processed by the current controller
        @param The stage (View) which will be controlled.
     */
    public void setCurrentStage(Stage stage);
    
    
    /*
       * sets the loaded FXML file as the next stage to be opened.
       @param The stage (View) which will be controlled.
    */
    public void setNextSource(Parent nextStage);
}
