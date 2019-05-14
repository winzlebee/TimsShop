
package TimsShop.Controllers.Dialogs;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.UserModels.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CustomerDialog implements Initializable
{

    @FXML
    private TextField searchBar;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView customerTable;
    
      //Table Components
    private TableColumn<Customer, Long> idCol;
    private TableColumn<Customer, String> lastNameCol;
    private TableColumn<Customer, String> firstNameCol;
    private TableColumn<Customer, String> emailCol;
    private TableColumn<Customer, Long> phoneCol;
    private TableColumn<Customer, Float> creditCol;
    private TableColumn<Customer, Boolean> memberCol;
    private TableColumn<Customer, String> interestsCol;

    
    
    
    
    

    private ShopDataStorage storage;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

       setTableHeader();
       setTableData();
    }
    

    
    public void setStorage(ShopDataStorage storage)
    {
        this.storage = storage;
        customerTable.setItems(storage.getCustomers());
    }
    
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
    
    
    @FXML
    private void searchBarHandler(KeyEvent event)
    {
    }

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
    private void modifyHandler(MouseEvent event)
    {
    }

    @FXML
    private void deleteHandler(MouseEvent event)
    {
    }
    
    
}
