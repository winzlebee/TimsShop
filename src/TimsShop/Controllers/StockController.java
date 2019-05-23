package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Supplier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.ObservableList;


public class StockController
{
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Supplier> suppliers;
    private ShopDataStorage storage;
    
    public StockController(ShopDataStorage storage)
    {
        this.storage = storage;
        toys = storage.getToys();
        suppliers = storage.getSuppliers();
        categories = storage.getCategories();
        
    }
    
    
    /******************STORAGE PROCEDURES**********************************/
    ///////////////////////////////////////////////////////////////////////
    public void makeInsertionRequest(String name, float price, String description, 
            long categoryId, int count, ArrayList<Long> suppliers, String location)
    {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDateTime currentDate = LocalDateTime.now();
        
        
        storage.insertToy(name, price, description, categoryId, count, suppliers, date.format(currentDate), location);
    }
    
    /**************************
     * Validate that key form  entries are mode
     * @param name - the toy name, entered from form
     * @param id the Category ID, entered from form
     * @return 
     *****************************************/
    public boolean isValidForm(String name, Long id)
    {
        if (name.isEmpty() || name.equals("".trim())) 
        {
            return false;
        }
        
        if (id == null)
        {
            return false;
        }
        
        return true;
    }
    
    
    /*********************GETTERS************************/
    /////////////////////////////////////////////////////////
    
    public boolean categoriesIsEmpty()
    {
        return categories.isEmpty();
    }
    public ObservableList<Category> getCategories()
    {
        return categories;
    }
    
    public ObservableList<Supplier> getSuppliers()
    {
        return suppliers;
    }
    
    public ObservableList<Toy> getToys()
    {
        return toys;
    }
    
    public String getCategiryName(long id)
    {
        return (storage.getCategoryById(id)).getName();
    }
    
    public String getFormattedSuppliers(Toy t)
    {
        return storage.getSupplierString(t);
    }
    
    
     
    
    
}
