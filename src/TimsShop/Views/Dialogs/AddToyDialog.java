package TimsShop.Views.Dialogs;

// Local Imports
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    }
    
    @FXML
    void onSubmit(MouseEvent event) {
        System.out.println("A toy has been appended.");
        
        if (nameField.getText().isEmpty()) {
            errMsgLabel.setText("Please enter a toy name.");
            return;
        }
        
        System.out.println(priceField.getValue());
        
        //TODO: Add toy handler code
        dataStorage.insertToy(nameField.getText(), priceField.getValueFactory().getValue().floatValue(), descriptionField.getText());
        
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
        // TODO: Load data on initialize
        //categoryField.setItems(dataStorage.getCategories());
    }

}