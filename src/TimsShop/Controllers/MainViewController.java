
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Controllers.Dialogs.AddCategoryDialog;
import TimsShop.Controllers.Dialogs.AddToyDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**************************************************************
 * The MainViewController 
 */
public class MainViewController implements  Initializable  
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
    @FXML
    private Button addCategoryButton;
    
    /**************TABLE COMPONENTS********************/
    @FXML 
    private TableView toyTable;
    private TableColumn<Toy, Long> idCol;
    private TableColumn<Toy, String> nameCol;
    private TableColumn<Toy, String> priceCol;
    private TableColumn<Toy, String> categoryCol;
    private ObservableList<Toy> toyData;
    
    
    //Data storage for the application
    private ShopDataStorage storage;

    
    /**********************************************************************
    Function: called to initialize a controller after its 
              root element has been completely processed.
    Arguments:  @param url locator for FXML document.
                @param rb bundles the locally configured language settings
    ************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 
        bindData();
        setTableHeaders();
        setTableData();
    }
    
    /***********************TABLE INITIALIZATION*******************************/
    private void bindData()
    {   // Initialize the data storage engine. It will create a database if it doesn't exist yet.
        storage = new ShopDataStorage();
        toyData = storage.getToys();
        toyTable.setItems(toyData);
     }
  
    private void setTableHeaders()
    {
        idCol = new TableColumn<>("Id");
        nameCol = new TableColumn<>("Name");
        priceCol = new TableColumn<>("Price");
        categoryCol = new TableColumn<>("Category");
    }
    private void setTableData()
    {
        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getId()));
        nameCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getName()));
        priceCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(String.format("$%.2f", p.getValue().getPrice())));
        categoryCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(storage.getCategoryById(p.getValue().getCategoryId()).getName()));
        toyTable.getColumns().addAll(idCol, nameCol, priceCol, categoryCol);
    }

    
    /*******************************EVENT LISTENERS****************************/
    public void onClose() {
        System.out.println("Saving database...");
        storage.write();
    }
    
    @FXML
    private void addCategoryHandler(MouseEvent evt) throws IOException {
        FXMLLoader catDialogLoader = new FXMLLoader(getClass().getResource("/TimsShop/Views/AddCategoryDialog.fxml"));
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
        FXMLLoader toyDialogLoader = new FXMLLoader(getClass().getResource("/TimsShop/Views/InsertToyDialog.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/TimsShop/Views/EmployeeLoginView.fxml"));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.show();
    }

    @FXML
    private void handleSearchEntry(KeyEvent event)
    {
        //Add Data to list based on filter
        FilteredList<Toy> filteredData = new FilteredList<>(toyData,  e -> true);
        
        //Listener compares table data to  string value entered by user in search bar
        searchBar.textProperty().addListener((observableValue, oldValue, newValue) -> 
        {   
            filteredData.setPredicate((Predicate<? super Toy>) toy -> 
            {   //If search bar is empty, no filter is applied
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                //Check search string is contained within name of toy
                return toy.getName().toLowerCase().contains(newValue.toLowerCase());
            });
            //Sort the filtered list based on insertion order, then bind the list to the table
            SortedList<Toy> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(toyTable.comparatorProperty());
            toyTable.setItems(sortedData);
        });
        
    }
    
    
}
