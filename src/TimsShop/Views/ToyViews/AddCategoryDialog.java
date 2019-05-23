package TimsShop.Views.ToyViews;

// Local Imports
import TimsShop.Models.DataModels.ShopDataStorage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCategoryDialog implements Initializable {
    
    @FXML
    private TextField catNameField;

    @FXML
    private TextField catTagsField;
    
    @FXML
    private Label errMsgLabel;
    
    private ShopDataStorage dataStorage;

  
    public void setStorage(ShopDataStorage storage) {
        this.dataStorage = storage;
    }
    
    @FXML
    void onSubmit(MouseEvent event) {
        
        if (catNameField.getText().isEmpty()) {
            errMsgLabel.setText("Please enter a category name.");
            return;
        }
        
        dataStorage.addCategory(catNameField.getText(), new ArrayList<String>(Arrays.asList(catTagsField.getText().split(","))));
        System.out.println("A category has been added.");
        
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
        // TODO: Load category data when editing on initialize
    }

}