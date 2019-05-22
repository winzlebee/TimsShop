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
                INSTANCE.controllerMap = new HashMap<>();
                //Add the view key to the map, and set the default value of all to nll
                for(Views views: Views.values())
                {
                    INSTANCE.stageMap.put(views.getKey(), null);
                    INSTANCE.stageMap.put(views.getKey(), null);
                }
            }      
            return INSTANCE;
        }
    }
    //Maps the stages and controllers of the system to the string key value
    private HashMap<String, Stage> stageMap;
    private HashMap<String, FXMLLoader> controllerMap;
    
    
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
            FXMLLoader loader = null;
            Stage stage = new Stage();
            try
            {   //Attempt to load FXML from Views directory
                loader = new FXMLLoader(getClass().getResource(view.getPath()));
                root = loader.load();
            
            } catch (IOException ex)
            {
                Logger.getLogger(ViewLoader.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stageMap.put(view.getKey(), stage);
            controllerMap.put(view.getKey(), loader);
        }
    }
    /******************************************************************
     * Renders the view to the screen.
     * @param view  enum representing the view intended to be rendered
     **********************************************************************/    
    public void show(Views view)
    {
        if(stageMap.get(view.getKey()) != null)
        {
            stageMap.get(view.getKey()).show();
        }
    }
    /******************************************************************
     * @param view
     * @return whether a successful close operation was executed
     *****************************************************************/
    public boolean close(Views view)
    {
        if(stageMap.get(view.getKey()) != null  && stageMap.get(view.getKey()).isShowing())
        {
            stageMap.get(view.getKey()).close();
            stageMap.remove(view.getKey());
            controllerMap.remove(view.getKey());
            return true;
        }
        return false;
    }
    /**********************************************
     * @param view 
     * @return the stage mapped to the key
     *********************************************/
    public Stage getStage(Views view)
    {
        return stageMap.get(view.getKey());
    }
    /*********************************************************
     * @param <T> the class type of the Controller.
       upon calling this, the result 
       should be casted to the appropriate controller class type
       controller class type
     * @param view
     * @return a view controller.
     *******************************************************/
    public <T> T getController(Views view)
    {
      return controllerMap.get(view.getKey()).<T>getController();
    }
    
    /*************************
     * Closes all open views 
     ***************************/
   public void notifyAllToClose()
    {
        for(String s: stageMap.keySet())
        {
            Stage openStage =  stageMap.get(s);
            if(openStage != null)
            {
                openStage.close();
            }
        }
    }
}
    
    