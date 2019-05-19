package TimsShop.Controllers.Dialogs;

// Local Imports
import TimsShop.Controllers.ViewLoader;
import TimsShop.Controllers.Views;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.UserModels.Supplier;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddToyDialog implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Double> priceField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ComboBox categoryField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitButton;
    @FXML
    private ComboBox supplierField;
    @FXML
    private Spinner<Double> amountField;
    @FXML
    private Button addSupplier;
    @FXML
    private TextArea supplierArea;
    
    
   
    private Label errMsgLabel;
    private ShopDataStorage dataStorage;
    private ArrayList<Long> supplierList;
    
    /********************INITIALIZATIONS**************************/
    //////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate initial values 
        supplierList = new ArrayList<>();
        initSpinners();  
    }
    
    public void initSpinners()
    {
        priceField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) 
        {
            priceField.increment(0); // won't change value, but will commit editor
        }
        });
        
        amountField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) 
        {
            amountField.increment(0); // won't change value, but will commit editor
        }
        });  
    }

    public void setStorage(ShopDataStorage storage) {
        this.dataStorage = storage;
        
        // Initialize necessary fields from storage
        categoryField.setItems(dataStorage.getCategories()); 
        supplierField.setItems(dataStorage.getSuppliers());
    }
    /********************EVENT HANDLERS**************************/
    //////////////////////////////////////////////////////////////
    @FXML
    void onSubmit(MouseEvent event) {
        
        if (nameField.getText().isEmpty()) {
            errMsgLabel.setText("Please enter a toy name.");
            return;
        }
        
        if (categoryField.getValue() == null) {
            errMsgLabel.setText("Please select a category for the toy.");
            return;
        }
        
        System.out.println(priceField.getValue().floatValue());
        
        //TODO: Add toy handler code
        dataStorage.insertToy(nameField.getText(), priceField.getValueFactory().getValue().floatValue(),
                descriptionField.getText(), ((Category) categoryField.getValue()).getID(), amountField.getValue().intValue(), supplierList  );
    }

    @FXML
    private void onCancel(MouseEvent event) {
        closeDialog(event);
    }
    
    private void closeDialog(MouseEvent evt) {
        ViewLoader.getInstance().close(Views.ADD_TOY);
    }
    @FXML
    private void addSupplierHandler(MouseEvent event)
    {
        supplierArea.appendText(((Supplier)supplierField.getSelectionModel().getSelectedItem()).toString());
        supplierList.add(((Supplier)supplierField.getSelectionModel().getSelectedItem()).getSupplierId());
        
    }



}