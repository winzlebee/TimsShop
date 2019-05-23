package TimsShop.Views.ToyViews;

// Local Imports
import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.StockController;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddToyDialog implements Initializable {
    
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
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
    private ArrayList<Long> supplierList;
    
    private StockController controller;
    
    /********************INITIALIZATIONS**************************/
    //////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate initial values 
        supplierList = new ArrayList<>();
        initController();
        initFields();
        initSpinners();  
    }
    private void initController()
    {
        controller = ApplicationController.getInstance().getStockController();
        
    }
    private void initFields()
    {
        categoryField.setItems(controller.getCategories());
        supplierField.setItems(controller.getSuppliers());
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

    /********************EVENT HANDLERS**************************/
    //////////////////////////////////////////////////////////////
    @FXML
    void onSubmit(MouseEvent event) {
        
        //Request Controller to validate form data
        if(controller.isValidForm(nameField.getText(), ((Category)categoryField.getValue()).getID()))
        {
            controller.makeInsertionRequest(nameField.getText(), priceField.getValueFactory().getValue().floatValue(),
                            descriptionField.getText(), ((Category) categoryField.getValue()).getID(),
                            amountField.getValue().intValue(), supplierList, " ");
        }
        else
        {
            //Show error message
            
        }
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
