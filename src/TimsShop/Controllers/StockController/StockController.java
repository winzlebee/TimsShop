package TimsShop.Controllers.StockController;

import TimsShop.Controllers.CallBackEvt;
import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Item;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.RelationModels.Sale;
import TimsShop.Models.UserModels.Customer;
import TimsShop.Models.UserModels.Supplier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import javafx.collections.ObservableList;


public class StockController
{
    /*********************CLASS FIELDS************************/
    /////////////////////////////////////////////////////////
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Supplier> suppliers;
    private ObservableList<Sale> sales;
    
    private Toy selectedToy;
    
    private CallBackEvt toyCallBack;
    private ShopDataStorage storage;
    
    public StockController(ShopDataStorage storage)
    {
        this.storage = storage;
        toys = storage.getToys();
        suppliers = storage.getSuppliers();
        categories = storage.getCategories();
        sales = storage.getSales();
    }
    
    
    /******************STORAGE PROCEDURES**********************************/
    ///////////////////////////////////////////////////////////////////////
    public void makeInsertionRequest(String name, float price, String description, 
            Object categoryId, int count, ArrayList<Long> suppliers, String location)
    {
        try{
        
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDateTime currentDate = LocalDateTime.now();
        
        
        storage.insertToy(name, price, description, ((Category)categoryId).getID(), count, suppliers, date.format(currentDate), location);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void insertCategory(String name, ArrayList<String> tags)
    {
        storage.addCategory(name, tags);
    }
    
    public void setSelectedToy(Toy t)
    {
        this.selectedToy = t;
    }
    
    public Toy getSelectedToy()
    {
        return selectedToy;
    }
    
    public void removeToy(Toy t) {
        this.storage.removeToy(t);
    }
    
    public void setToyQuantity(Toy t, int quantity) {
        t.setStockCount(quantity);
        toyCallBack.callBack();
    }
    
    public void makeSale(Customer cust, Map<Item, Integer> quantities)
    {
        // Remove each of the item quantities where necessary
        for (Item i : quantities.keySet()) {
            int quantity = quantities.get(i);
            i.removeStock(quantity);
        }
        
        storage.insertSale(quantities, cust);
        
        toyCallBack.callBack();
    }
    
    public void addSupplier(String name, String address, String contact, long phoneNum)
    {
        storage.addSupplier(name, address, contact, phoneNum);
    }
    
    public void removeSupplier(Supplier supp) {
        storage.removeSupplier(supp);
        toyCallBack.callBack();
    }
    
    public void setRefreshCallback(CallBackEvt evt)
    {
        toyCallBack = evt;
    }
    
    
    /**************************
     * Validate that key form  entries are mode
     * @param name - the toy name, entered from form
     * @param id the Category ID, entered from form
     * @return 
     *****************************************/
    public boolean isValidForm(String name, Category cat)
    {
        try {
            if (name.isEmpty() || name.equals("".trim())) 
            {
                return false;
            }

            if (cat == null)
            {
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    
    /*********************GETTERS************************/
    /////////////////////////////////////////////////////////
    
    public double getSalePrice(Sale sale) {
        double total = 0;
        for (Long itemIndex : sale.getSaleItems().keySet()) {
            Toy t = storage.getToyById(itemIndex);
            if (t == null) continue;
            total += t.getPrice() * sale.getSaleItems().get(itemIndex);
        }
        return total;
    }
    
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
    
    public ObservableList<Sale> getSales()
    {
        return sales;
    }
    
    public String getCategoryName(long id)
    {
        return (storage.getCategoryById(id)).getName();
    }
    
    public String getFormattedSuppliers(Toy t)
    {
        return storage.getSupplierString(t);
    }
    
}
