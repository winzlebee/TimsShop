
package TimsShop.Controllers.Dialogs.CustomerControllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CustomerDialog implements Initializable
{
    /******************************CLASS FIELDS******************************/
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField searchBar;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField customerSelected;
    @FXML
    private TableView customerTable;
    @FXML
    private Button refreshButton;
    @FXML
    private ChoiceBox filterChoice;
    
    //Table Components
    private TableColumn<Customer, Long> idCol;
    private TableColumn<Customer, String> lastNameCol;
    private TableColumn<Customer, String> firstNameCol;
    private TableColumn<Customer, String> emailCol;
    private TableColumn<Customer, Long> phoneCol;
    private TableColumn<Customer, Float> creditCol;
    private TableColumn<Customer, Boolean> memberCol;
    private TableColumn<Customer, String> interestsCol;
    
    private int selectedCol;
    private ShopDataStorage storage;
    private Alert deleteAlert;
    
    /******************************INITIALIZATIONS******************************/
    //////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {   
        initPopups();
        initSearchChoices();
        setTableHeader();
        setTableData();
        toggleButtonEnable(false);
        
        customerTable.getSelectionModel().selectedIndexProperty().addListener(obs -> handleSelectedItem());
    }
    /**********************************************************
     * Sets the shop instance that is passed in from the main
     * @param storage - the current storage instance
     * containing shop data
     **********************************************************/
    public void setStorage(ShopDataStorage storage)
    {
        this.storage = storage;
        customerTable.setItems(storage.getCustomers());
    }
    
    private void initPopups()
    {
        deleteAlert = new Alert(AlertType.CONFIRMATION);
        deleteAlert.setContentText("WARNING: Deleting Customer Record in Progress");
        deleteAlert.setTitle("Comfirmation Dialog");
        deleteAlert.setHeaderText("Are you sure you want to delete " + customerSelected.getText() + "? ");
    }
    private void initSearchChoices()
    {
        filterChoice.setItems(FXCollections.observableArrayList("Last Name", "Email", "ID"));
        filterChoice.getSelectionModel().selectFirst();
    }


    /*************************TABLE INITIALIZATION****************************/
    //////////////////////////////////////////////////////////////////////////
    private void setTableHeader()
    {
        idCol = new TableColumn<>("Id");
        lastNameCol = new TableColumn<>("Last Name");
        firstNameCol = new TableColumn<>("First Name");
        emailCol = new TableColumn<>("Email");
        phoneCol = new TableColumn<>("Phone #");
        creditCol = new TableColumn<>("Store Credit ");
        memberCol = new TableColumn<>("Member");
        interestsCol = new TableColumn<>("Interests");
    }
    private void setTableData()
    {
        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getUserID()));
        lastNameCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getLastName()));
        firstNameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getFirstName()));
        emailCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getEmail()));
        phoneCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getPhoneNum()));
        creditCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getStoreCredit()));
        memberCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().isMember()));
        interestsCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().interestsToString()));
        customerTable.getColumns().addAll(idCol, lastNameCol, firstNameCol, emailCol, 
                                            phoneCol, creditCol, memberCol, interestsCol);
    }
    
    /*************************EVENT LISTENERS*********************************/
    //////////////////////////////////////////////////////////////////////////
   
    /********************************************************************
    * handles the loading of add customer dialog
    * IOException - FXML couldn't be found at path/couldn't be loaded
    *******************************************************************/ 
    @FXML
    private void addHandler(MouseEvent event) throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader addCustomerLoader = new FXMLLoader(getClass().getResource("/TimsShop/Views/AddCustomerDialog.fxml"));
        Parent addCustomerDialog = addCustomerLoader.load();
        AddCustomer dialog = addCustomerLoader.<AddCustomer>getController();
        
        dialog.setStorage(storage);
        
        Scene scene = new Scene(addCustomerDialog);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyHandler(MouseEvent event) throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader modifyCustomerLoader = new FXMLLoader(getClass().getResource("/TimsShop/Views/ModifyCustomerDialog.fxml"));
        Parent modifyCustomerDialog = modifyCustomerLoader.load();
        ModifyCustomer dialog = modifyCustomerLoader.<ModifyCustomer>getController();
        //sets the storage and call back function to refersh the table upon modification
        dialog.setCustomer((Customer)customerTable.getSelectionModel().getSelectedItem(), () -> customerTable.refresh());
        
        Scene scene = new Scene(modifyCustomerDialog);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void deleteHandler(MouseEvent event)
    {
       
        ButtonType deleteButton = new ButtonType("Delete");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        deleteAlert.getButtonTypes().setAll(deleteButton, cancelButton);
        Optional<ButtonType> result =  deleteAlert.showAndWait();
         
         if(result.get() == deleteButton)
         {
             deleteCustomer();
         } 
    }
    
    /******************************************
     * Deletes Customer from shop data
     *******************************************/
    private void deleteCustomer()
    {
        storage.getCustomers().remove((Customer)customerTable.getSelectionModel().getSelectedItem());
    }
    

    @FXML
    private void searchBarHandler(KeyEvent event)
    {
        Predicate<? super Customer> predicate; 
        //Add Data to list based on filter
        FilteredList<Customer> filteredData = new FilteredList<>(storage.getCustomers(),  e -> true);
        //Listener compares table data to  string value entered by user in search bar
        searchBar.textProperty().addListener((observableValue, oldValue, newValue) -> 
        {   
            filteredData.setPredicate( getSearchPredicate(newValue));
            SortedList<Customer> sortedData = new SortedList<>(filteredData); //Sort the filtered list based on insertion order, then bind the list to the table
            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortedData);
        }); 
    }
    
    private Predicate<? super Customer> getSearchPredicate(String value)
    {
        //if search field is empty, all customers are included in the filtered list
        if(searchBar.getText().isEmpty() || searchBar.getText().equals("".trim()))
        {
            return customer  ->  true;
        }
        //Check the set value of the choice box, and return the appropriate predicate
        if(((String)filterChoice.getValue()).equals("Last Name"))
        {
            return customer -> customer.getLastName().toLowerCase().contains(value.toLowerCase());
        }
        else if(((String)filterChoice.getValue()).equals("Email"))
        {
            return customer -> customer.getEmail().toLowerCase().contains(value.toLowerCase());
        }
        else if(((String)filterChoice.getValue()).equals("ID"))
        {
            return customer -> customer.getUserID() == Long.parseLong(value);
        }    
        return null;
    }

    
    /********************************************************************
     * Sets the selected item of the table to the global value of the class 
     * Toggles modify/delete buttons to be enabled
     *******************************************************************/ 
    private void handleSelectedItem()
    {
        selectedCol = customerTable.getSelectionModel().selectedIndexProperty().get();
        customerSelected.setText(lastNameCol.getCellData(selectedCol) + " " + firstNameCol.getCellData(selectedCol).charAt(0)+".");
        toggleButtonEnable(true);
    }
    
    /****************************************************** 
    * Toggles modify/delete buttons to be enabled/disabled
    ******************************************************/ 
    private void toggleButtonEnable(boolean value)
    {
        modifyButton.setDisable(!value);
        deleteButton.setDisable(!value);
    }
    
    /*****************************************
     * Reloads Data after a modification 
     ***************************************/
    @FXML
    private void refreshHandler(MouseEvent event)
    {
        customerTable.refresh();
    }
  
}
