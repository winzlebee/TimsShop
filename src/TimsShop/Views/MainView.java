
package TimsShop.Views;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Views.Dialogs.AddCategoryDialog;
import TimsShop.Views.Dialogs.AddToyDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


public class MainView implements  Initializable  
{
        
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button orderingButton;
    @FXML
    private Button browseButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button logoutButton;
    
    // Table components
    @FXML 
    private TableView toyTable;
    
    // Data storage for the application
    ShopDataStorage storage;
    
    /**********************************************************************
    Function: called to initialize a controller after its 
              root element has been completely processed.
    Arguments:  @param url locator for FXML document.
                @param rb bundles the locally configured language settings
    ************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // Initialize the data storage engine. It will create a database if it doesn't exist yet.
        storage = new ShopDataStorage();
        
        toyTable.setItems(storage.getToys());
        
        TableColumn<Toy, Long> idCol = new TableColumn<Toy, Long>("Id");
        TableColumn<Toy, String> nameCol = new TableColumn<Toy, String>("Name");
        TableColumn<Toy, String> priceCol = new TableColumn<Toy, String>("Price");
        
        idCol.setCellValueFactory(new Callback<CellDataFeatures<Toy, Long>, ObservableValue<Long>>() {
            public ObservableValue<Long> call(CellDataFeatures<Toy, Long> p) {
                return new ReadOnlyObjectWrapper<Long>(p.getValue().getId());
            }
         });
        
        nameCol.setCellValueFactory(new Callback<CellDataFeatures<Toy, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Toy, String> p) {
                return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
            }
         });
        
        priceCol.setCellValueFactory(new Callback<CellDataFeatures<Toy, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Toy, String> p) {
                return new ReadOnlyObjectWrapper<String>(String.format("$%.2f", p.getValue().getPrice()));
            }
         });
        
        toyTable.getColumns().addAll(idCol, nameCol, priceCol);
    }
    
    public void onClose() {
        System.out.println("Saving database...");
        storage.write();
    }
    
    @FXML
    private void addCategoryHandler(MouseEvent evt) throws IOException {
        FXMLLoader catDialogLoader = new FXMLLoader(getClass().getResource("/TimsShop/FXML/AddCategoryDialog.fxml"));
        Parent catDialogRoot = catDialogLoader.load();
        AddCategoryDialog dialog = catDialogLoader.<AddCategoryDialog>getController();
        
        // Set the storage method for the dialog
        dialog.setStorage(storage);
        
        Scene dialogScene = new Scene(catDialogRoot);
        Stage dialogStage = new Stage();
        
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
    
    @FXML
    private void insertHandler(MouseEvent evt) throws IOException {
        
        if (storage.getCategories().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Please define a category before adding a toy.");
            a.showAndWait();
            return;
        }
        
        // Function to launch the dialog to insert certain items into the database
        FXMLLoader toyDialogLoader = new FXMLLoader(getClass().getResource("/TimsShop/FXML/InsertToyDialog.fxml"));
        Parent toyDialogRoot = toyDialogLoader.load();
        AddToyDialog dialog = toyDialogLoader.<AddToyDialog>getController();
        
        // Set the storage method for the dialog
        dialog.setStorage(storage);
        
        Scene dialogScene = new Scene(toyDialogRoot);
        Stage dialogStage = new Stage();
        
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    @FXML
    private void logoutHandler(MouseEvent event) throws IOException
    {
             /*********************************
                TODO:
                 * Report staff member logged in 
                 * Close login stage- DONE
            **********************************/
            //Close login stage
            Stage currentStage = (Stage)logoutButton.getScene().getWindow();
            currentStage.close();
           
            //Load Main Stage FXML
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/TimsShop/FXML/EmployeeLoginView.fxml"));
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.show();
    }
    
}
