package TimsShop.Views.Dialogs;

// Local Imports
import TimsShop.Models.ItemModels.Toy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddToyDialog implements Initializable {
    
    @FXML
    private TextField nameField;

    @FXML
    private Spinner priceField;
    
    private ObservableList<Toy> localToyList;

    public void setAppMainObservableList(ObservableList<Toy> tvObservableList) {
        this.localToyList = tvObservableList;
        
    }
    
    @FXML
    void onSubmit(MouseEvent event) {
        System.out.println("A toy has been appended.");
        
        //TODO: Add toy handler code
        
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
    }

}