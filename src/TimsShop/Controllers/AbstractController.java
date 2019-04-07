
package TimsShop.Controllers;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;


public interface AbstractController 
{
    public void closeView() ;
    public void openNext() throws IOException;
    public void passControl(AbstractController nextController);
    public void setCurrentStage(Stage stage);
    public void setNextSource(Parent nextStage);
}
