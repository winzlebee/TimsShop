
package TimsShop.Views;

import TimsShop.Controllers.ApplicationController;
import TimsShop.Controllers.StockController;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Controllers.StorageSettable;
import TimsShop.Controllers.Views;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**************************************************************
 *  The MainView is used to handle 
    the navigation between the root view of the application and 
    the its branches. While also serving as the primary event 
    handling controller for the mainview
 ************************************************************/
public class MainView implements  Initializable
{
     /**************CLASS FIELDS ********************/
    //////////////////////////////////////////////////  
    @FXML
    private TextField searchBar;
    @FXML
    private Button saleButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button customerButton; 
    @FXML 
    private TableView toyTable;
    @FXML
    private Button supplierButton;
    @FXML
    private TextField supplerSearchBar;
    @FXML
    private TableView supplierTable;
    
    //Table Components
    private TableColumn<Toy, Long> idCol;
    private TableColumn<Toy, String> nameCol;
    private TableColumn<Toy, String> priceCol;
    private TableColumn<Toy, String> categoryCol;
    private TableColumn<Toy, Integer> qtyCol;
    private TableColumn<Toy, String> dateOrdered;
    private TableColumn<Toy, String> locationCol;
    private TableColumn<Toy, String> supplierCol;
    
    
  
    //Data storage for the application
    private ShopDataStorage storage;
    private static StockController stockController;
    
  
    /**********************************************************************
    called to initialize a controller after its 
              root element has been completely processed.
    Arguments:  @param url locator for FXML document.
                @param rb bundles the locally configured language settings
    ************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 
        initController();
        bindData();
        setTableHeaders();
        setTableData();
    }
    
    private void initController()
    {
        stockController = ApplicationController.getInstance().getStockController();
    }
    
    private void bindData()
    {
        toyTable.setItems(stockController.getToys());
        supplierTable.setItems(stockController.getToys());
    }
    

    /***********************TABLE INITIALIZATION*******************************/
    ///////////////////////////////////////////////////////////////////////////
    private void setTableHeaders()
    {
        //Toy Table
        idCol = new TableColumn<>("Id");
        nameCol = new TableColumn<>("Name");
        priceCol = new TableColumn<>("Price");
        categoryCol = new TableColumn<>("Category");
        qtyCol = new TableColumn<>("In Stock");
        
        //Supplier Table
        dateOrdered = new TableColumn<>("Date Stocked");
        locationCol = new TableColumn<>("Store Location");
        supplierCol = new TableColumn<>("Suppliers");
  
    }
    private void setTableData()
    {
        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getId()));
        nameCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getName()));
        priceCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(String.format("$%.2f", p.getValue().getPrice())));
        categoryCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(stockController.getCategiryName(p.getValue().getCategoryId())));
        qtyCol.setCellValueFactory( p -> new ReadOnlyObjectWrapper<>(p.getValue().getStockCount()));
        dateOrdered.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getDateStocked()));
        locationCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getStoreLocation()));
        supplierCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(stockController.getFormattedSuppliers(p.getValue())));
        
        
        toyTable.getColumns().addAll(idCol, nameCol, priceCol, categoryCol, qtyCol);
        supplierTable.getColumns().addAll(idCol,nameCol, dateOrdered, locationCol, supplierCol);
    }

    /*******************************EVENT LISTENERS****************************/
    ///////////////////////////////////////////////////////////////////////////
    /********************************************************
     * Displays add category dialog
     * @param event - on Mouse click event
     * @throws IOException - reports when fxml is unloadable
     *******************************************************/
    @FXML
    private void addCategoryHandler(MouseEvent evt) throws IOException {
        ApplicationController.getInstance().display(Views.ADD_CATEGORY);
    }
    /********************************************************
     * Displays insert toy dialog
     * @param event - on Mouse click event
     * @throws IOException - reports when fxml is unloadable
     *******************************************************/
    @FXML
    private void insertHandler(MouseEvent evt) throws IOException {
        
        if (stockController.categoriesIsEmpty()) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING, "Please define a category before adding a toy.");
            a.showAndWait();
            return;
        }
        //Display Dialog
        ApplicationController.getInstance().display(Views.ADD_TOY);
           
    }
    
    @FXML
    private void saleHandler(MouseEvent event) throws IOException {
        ApplicationController.getInstance().display(Views.ADD_SALE);
    }
    
    /********************************************************
     * Destroys mainview and displays login view
     * @param event - on Mouse click event
     * @throws IOException - reports when fxml is unloadable
     *******************************************************/
    @FXML
    private void logoutHandler(MouseEvent event) throws IOException
    {
        ApplicationController.getInstance().display(Views.LOGIN);
        ApplicationController.getInstance().closeView(Views.LOGIN);
        
    }

    @FXML
    private void supplierButtonHandler(MouseEvent event)
    {
        ApplicationController.getInstance().display(Views.SUPPLIER);
    }
    
    /********************************************************
     * Displays customer dialog
     * @param event - on Mouse click event
     * @throws IOException - reports when fxml is unloadable
     *******************************************************/
    @FXML
    private void customerButtonHandler(MouseEvent event) throws IOException
    {
        ApplicationController.getInstance().display(Views.CUSTOMER);
    }
    
    /********************************************************
    * Handles dynamic filter/search and redraws the table 
    * @param event - on keypressed  click event
    *******************************************************/
    @FXML
    private void handleSearchEntry(KeyEvent event)
    {
        //Add Data to list based on filter
        FilteredList<Toy> filteredData = new FilteredList<>(storage.getToys(),  e -> true);
        
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
