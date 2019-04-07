package TimsShop.Views.Dialogs;

// Local Imports
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Label errMsgLabel;
    
    private ShopDataStorage dataStorage;

    public void setStorage(ShopDataStorage storage) {
        this.dataStorage = storage;
        
        // Initialize necessary fields from storage
        categoryField.setItems(dataStorage.getCategories()); 
    }
    
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
        
        System.out.println(priceField.getValue());
        
        //TODO: Add toy handler code
        dataStorage.insertToy(nameField.getText(), priceField.getValueFactory().getValue().floatValue(), descriptionField.getText(), ((Category) categoryField.getValue()).getID());
        System.out.println("A toy has been appended.");
        
        closeDialog(event);
    }

    @FXML
    private void onCancel(MouseEvent event) {
        closeDialog(event);
    }
    
    private void closeDialog(MouseEvent evt) {
        Node  source = (Node)  evt.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate initial values
    }

}