
package TimsShop.Controllers.Dialogs.SupplierControllers;

import TimsShop.Controllers.Dialogs.CustomerControllers.CallBackEvt;
import TimsShop.Controllers.ViewLoader;
import TimsShop.Controllers.Views;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.UserModels.Supplier;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class SupplierView implements Initializable
{
    /******************************CLASS FIELDS******************************/
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField searchBar;
    @FXML
    private ChoiceBox filterChoice;
    @FXML
    private TextField supplierSelected;
    @FXML
    private TableView supplierTable;

    private TableColumn<Supplier, Long> idCol;
    private TableColumn<Supplier, String> nameCol;
    private TableColumn<Supplier, String> addressCol;
    private TableColumn<Supplier, String> contactCol;
    private TableColumn<Supplier, Long> phoneCol;
    
    private int selectedCol;
    private Alert deleteAlert;
    private CallBackEvt evnt;
            
    
    
    private ShopDataStorage storage;
    /******************************INITIALIZATIONS******************************/
    //////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initPopups();
        setTableHeader();
        setTableData();
        toggleButtonEnable(false);
        
        supplierTable.getSelectionModel().selectedIndexProperty().addListener(obs -> handleSelectedItem());
    
    }
    
    public void setStorage(ShopDataStorage storage, CallBackEvt e)
    {
        this.storage = storage;
        supplierTable.setItems(storage.getSuppliers());
        evnt = e;
        
    }
    private void initPopups()
    {
        deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setContentText("WARNING: Deleting Supplier  Record in Progress");
        deleteAlert.setTitle("Comfirmation Dialog");

    }
     /*************************TABLE INITIALIZATION****************************/
    //////////////////////////////////////////////////////////////////////////
    private void setTableHeader()
    {
        idCol = new TableColumn<>("Id");
        nameCol = new TableColumn<>("Business Name");
        addressCol = new TableColumn<>("Address");
        contactCol = new TableColumn<>("Contact");
        phoneCol = new TableColumn<>("Phone Number");
    }

    private void setTableData()
    {
        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getSupplierId()));
        nameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getBusinessName()));
        addressCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getAddress()));
        contactCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getContactPerson()));
        phoneCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getPhoneNum()));
        
        supplierTable.getColumns().addAll(idCol, nameCol, addressCol, contactCol, phoneCol);
    }
    
    
    /*************************EVENT LISTENERS*********************************/
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private void modifyHandler(MouseEvent event)
    {
        
    }
    
    /*********************************************************
     * Prompts the user to confirms deletion of a supplier record
     * @param event on click event
     **********************************************************/
    @FXML
    private void deleteHandler(MouseEvent event)
    {   //Button Initialisation
        ButtonType delete = new ButtonType("Delete");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        //Set Dialog Properties
        deleteAlert.getButtonTypes().setAll(delete, cancelButton);
        deleteAlert.setHeaderText("Are you sure you want to delete " + supplierSelected.getText() + "? ");
        
        //Delete button on comfirmation prompt is clicked
        Optional<ButtonType> result =  deleteAlert.showAndWait();
        if(result.get() == delete)
        {
            deleteSupplier();
        } 
    }

    @FXML
    private void addHandler(MouseEvent event)
    {
        ViewLoader.getInstance().load(Views.ADD_SUPPLIER);
        ViewLoader.getInstance().show(Views.ADD_SUPPLIER);
        ((AddSupplierView)ViewLoader.getInstance().getController(Views.ADD_SUPPLIER)).setStorage(storage, () -> supplierTable.refresh());
    }

    @FXML
    private void searchBarHandler(KeyEvent event)
    {
    }

    /********************************************************************
     * Sets the selected item of the table to the global value of the class 
     * Toggles modify/delete buttons to be enabled
     *******************************************************************/ 
    private void handleSelectedItem()
    {
        selectedCol = supplierTable.getSelectionModel().selectedIndexProperty().get();
        supplierSelected.setText(nameCol.getCellData(selectedCol));
        toggleButtonEnable(true);
    }
    private void toggleButtonEnable(boolean value)
    {
        modifyButton.setDisable(!value);
        deleteButton.setDisable(!value);
    }
        
    private void deleteSupplier()
    {
        storage.getSuppliers().remove((Supplier)supplierTable.getSelectionModel().getSelectedItem());
        evnt.callBack();
    }
  
    
    
}
