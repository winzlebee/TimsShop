
package TimsShop.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**********************************************************
 * The FXMLLoader is a factory class used to build FXML views
 ************************************************************/
public class ViewLoader 
{
    private static class SingletonHolder
    {
        static ViewLoader INSTANCE = createInstance();
        private SingletonHolder(){}
        private static ViewLoader createInstance()
        {
            if(INSTANCE == null )
            {
                INSTANCE = new ViewLoader();
                INSTANCE.stageMap = new HashMap<>();
                //Add the view key to the map, and set the default value of all to nll
                for(Views views: Views.values())
                {
                    INSTANCE.stageMap.put(views.getKey(), null);
                }
            }      
            return INSTANCE;
        }
    
    }
    private HashMap<String, Stage> stageMap;
    
    
    private ViewLoader(){}
    
    public static ViewLoader getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    
    public  void load(Views view)
    {
        //Checks if view is already loaded into the map
        if(stageMap.get(view.getKey()) == null)
        {   
            Parent root = null;
            Stage stage = new Stage();
            try
            {   //Attempt to load FXML from Views directory
                root = FXMLLoader.load(getClass().getResource(view.getPath()));
            } catch (IOException ex)
            {
                Logger.getLogger(ViewLoader.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stageMap.put(view.getKey(), stage);
        }
    }
        
    public  void show(Views view)
    {
        if(stageMap.get(view.getKey()) != null)
        {
            stageMap.get(view.getKey()).show();
        }
    }
    
    
    
}
