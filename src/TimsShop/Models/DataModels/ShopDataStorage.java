/******************************************************************************
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Models.DataModels;

import DatabaseEngines.AbstractDatabase;
import DatabaseEngines.TextFileDatabase;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Item;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.RelationModels.Sale;
import TimsShop.Models.UserModels.Customer;
import TimsShop.Models.UserModels.Employee;
import TimsShop.Models.UserModels.Supplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/****************************************************
 * @author win
 * the ShopDataStorage defines the business logic for 
 * interfacing with the storage units of the system
 ***************************************************************/
public class ShopDataStorage {
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Employee> employees;
    private ObservableList<Customer> customers;
    private ObservableList<Supplier> suppliers;
    private ObservableList<Sale> sales;
    
    private AbstractDatabase storageEngine;
    
    public ShopDataStorage() {
        storageEngine = new TextFileDatabase();
        
        if (storageEngine.hasDataStorage()) {
            storageEngine.readDataStorage(this);
            return;
        }
        
        // If there's no data storage in place, we're going to initialize our observables as empty lists.
        toys = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        employees = FXCollections.observableArrayList();
        customers = FXCollections.observableArrayList();
        suppliers = FXCollections.observableArrayList();
        sales = FXCollections.observableArrayList();
        
        //TEST----------------------------------------------------
        LOAD_TEST_DATA();
        
    }
    /////////////////////////////////////////////////////////////
    /********************TEST DATA *****************************/
    //////////////////////////////////////////////////////////////
    
    public void LOAD_TEST_DATA()
    {
        categories.add(new Category(1, "Locomotives", null));
        categories.add(new Category(2, "WWI Aircraft", null));
        categories.add(new Category(3, "WWII Aircraft", null));
        categories.add(new Category(4, "Cruise Liners", null));
        categories.add(new Category(5, "WWI Heavy Artillery", null));
        categories.add(new Category(6, "Sports Cars", null));
        categories.add(new Category(7, "Science Fiction", null));
        categories.add(new Category(8, "Drone", null));
        
        suppliers.add(new Supplier(1, "Loco-Moco Pty. Ltd.", "12 Downing St ", "Phillip Ambrose", 0432526));
        suppliers.add(new Supplier(2, "C4Lyfe.", "16 May Ave", "Danuek Kreepa", 04325236));
        suppliers.add(new Supplier(3, "Crowd Model.", "9 Reagent St ", "Henrieeta Clarenace", 04325236));
        suppliers.add(new Supplier(4, "Mad-House", "305 Baker Crsnt", "Alfred Connie", 0432526));
        suppliers.add(new Supplier(5, "Superb Supplier.", "128 Downing Street ", "Regenald Rowey", 0432526));
        
        ArrayList<Long> sList = new ArrayList<>();
        sList.add((long)1);
        sList.add((long)2);
        
        ArrayList<Long> sList2 = new ArrayList<>();
        sList2.add((long)3);
        
        ArrayList<Long> sList3 =  new ArrayList<>();
        sList3.add((long)4);
        sList3.add((long)1);
        sList3.add((long)5);
        
        toys.add(new Toy(1, "Loco-Master-Pro", 82, 1, 5, sList, "", "20/05/2019","Aisle 1") );
        toys.add(new Toy(2, "DoodleBug", 62, 3, 1, "", "20/05/2019", "Aisle 1") );
        toys.add(new Toy(3, "Aircraft Carrier", 82, 4, 6, sList2, "", "20/05/2019", "Aisle 1") );
        toys.add(new Toy(4, "Fast Car V1", 82, 6, 5, sList2, "", "20/05/2019", "Aisle 1") );
        toys.add(new Toy(5, "SS Enterprise", 182, 7, 5, "", "20/05/2019", "Aisle 1") );
        toys.add(new Toy(6, "SS Discovery", 125, 7, 5, sList3, "", "20/05/2019", "Aisle 2") );
        toys.add(new Toy(7, "VI Tank", 195,5, 5,sList , "", "20/05/2019", "Aisle 3") );
        toys.add(new Toy(8, "VII Tank", 150 ,5, 5,sList ,"", "20/05/2019", "Aisle 4") );
        toys.add(new Toy(9, "MX5 Deluxe Edition", 200, 6, 5,sList2 , "", "20/05/2019", "Aisle 2") );
        toys.add(new Toy(10, "Steam liner", 1100,1, 5,sList3 , "", "20/05/2019", "Aisle 3") );
        toys.add(new Toy(11, "DJI F450", 500, 8, 5, sList3, "Large quadcopter with support for gimbal system.", "24/05/2019", "Aisle 1") );
        
        
        ArrayList<String> iList = new ArrayList<>();
        iList.add("Locomotives");
        iList.add("Science Fiction");
        iList.add("WWII Aircraft");
         
        customers.add(new Customer(1 ,"Norman", "Bates", "nBato1950@aapt.net.au", 04672526, "19/05/2019", 0, true, iList));
        customers.add(new Customer(2 ,"Alfred", "Elgiggle", "ae@gmail.com", 043252216, "19/05/2019", 0, false, iList));
        customers.add(new Customer(3 ,"Gerald", "George", "Geegee@hotmail.com", 0432526, "19/05/2019", 0, true, iList));
        customers.add(new Customer(4 ,"Susie", "Sussex", "sussantor@uowmail.edu.au", 0432526, "19/05/2019", 0, true, iList));
        customers.add(new Customer(5 ,"Georgina", "Goolieo", "gina@gmail.com", 04332726, "19/05/2019", 0, true, iList));
        customers.add(new Customer(6 ,"Maryanne", "Hellyeah", "Mh@gmail.com", 04332726, "19/05/2019", 0, true, iList));
        customers.add(new Customer(7 ,"Arnold", "Fruns", "Afuzzo@gmai.com", 0432526, "19/05/2019", 0, true, iList));

        Map<Item, Integer> sale = new HashMap<>();
        sale.put(toys.get(0), 2);
        sale.put(toys.get(4), 1);
        sales.add(new Sale(sale, customers.get(0)));
    }
  
    /////////////////////////////////////////////////////////////
    /********************END TEST DATA *****************************/
    //////////////////////////////////////////////////////////////
     public void write() {
        // Write the data storage to the specified storage engine
        storageEngine.writeDataStorage(this);
    }
     
    /****************************SALE******************************/
    //////////////////////////////////////////////////////////////
      
    public ObservableList<Sale> getSales() {
        return sales;
    }
    
    public void setSales(ArrayList<Sale> newSales) {
        this.sales = FXCollections.observableArrayList(newSales);
    }
    
    public void insertSale(Map<Item, Integer> items, Customer cust) {
        // Insert a sale into the database. Note that a sale can include a number of toys
        sales.add(new Sale(items, cust));
    }
    
    public void removeSale(Sale s) {
        sales.remove(s);
    }
     
    /****************************TOY******************************/
    //////////////////////////////////////////////////////////////
    public ObservableList<Toy> getToys() {
        return toys;
    }
    public void setToys(ArrayList<Toy> newToys) {
        this.toys = FXCollections.observableArrayList(newToys);
    }
    
    public void insertToy(String name, float price, String description, long categoryId, int count, ArrayList<Long> suppliers, String date, String location) {
        toys.add(new Toy(getLastToyId() + 1, name, price,categoryId, count, suppliers, description, date, location) );
    }
    
    public void removeToy(Toy t)
    {
        toys.remove(t);
    }
    
    private long getLastToyId() {
        if (toys.isEmpty()) return 0;
        return toys.get(toys.size()-1).getId();
    }
    
    public Toy getToyById(long id) {
        for (Toy t : toys) {
            if (t.getId() == id) return t;
        }
        return null;
    }
    
    /****************************************************************************
     * @param toy the toy of which will have formated display of their supplier list
     * @return a toys supplier list concatenated into a single string 
     *****************************************************************************/
    public String getSupplierString(Toy toy)
    {
        String str = "";
        try
        {   //If getSuppliers() is null, catch
            ArrayList<Long> supList = new ArrayList<>(toy.getSupliers());
            if(!supList.isEmpty() || supList == null)
            {
                for(int i = 0; i < supList.size(); i++)
                {
                    str += "- "+ getSupplierById(supList.get(i)).getBusinessName() + "\n";
                }      
            }
            else
            {
                str = "None";
            }
        }
        catch(NullPointerException e)
        {
            str = "None";
        }
        return str;
    }
    
    
    /****************************CATEGORY******************************/
    ///////////////////////////////////////////////////////////////////
    public ObservableList<Category> getCategories() {
        return categories;
    }
        
    public void setCategories(ArrayList<Category> newCategories) {
        this.categories = FXCollections.observableArrayList(newCategories);
    }
    
    public void addCategory(String name, ArrayList<String> tags) {
      // Inserts a toy into the shop data model
      categories.add(new Category(getLastCategoryId()+1, name, tags));
  }

    private long getLastCategoryId() {
        return categories.isEmpty() ? 0 : categories.get(categories.size()-1).getID();
    }
    
    public Category getCategoryById(long id) {
        return categories.stream().filter(x -> x.getID() == id).findFirst().orElse(null);
    }
    
    /****************************CUSTOMER******************************/
    ///////////////////////////////////////////////////////////////////
    public ObservableList<Customer> getCustomers()
    {
        return customers;
    }
    
    public void setCustomers(ArrayList<Customer> newCustomers)
    {
        this.customers = FXCollections.observableArrayList(newCustomers);
    }
    
    public void addCustomer(String firstName, String lastName, String email, long phoneNum, String dateOfJoining,  float storeCredit, boolean isMember, ArrayList<Long> interests)
    {
        ArrayList<String> iList = null;
        
        if(interests != null)
        { 
            iList = new ArrayList<>();
            for (Long l : interests)
            {
                iList.add(getCategoryById(l).getName());
            }
        }
        customers.add(new Customer(getLastCustomerId() + 1 ,firstName, lastName, email, phoneNum, dateOfJoining, storeCredit, isMember, iList));
    }
    
    
    private long getLastCustomerId()
    {
        return customers.isEmpty() ? 0 : customers.get(customers.size()-1).getUserID();
    }
    
    /****************************SUPPLIER******************************/
    ///////////////////////////////////////////////////////////////////   
    public ObservableList<Supplier> getSuppliers()
    {
        return suppliers;
    }
    public void setSuppliers(ArrayList<Supplier> newSuppliers)
    {
        this.suppliers = FXCollections.observableArrayList(newSuppliers);
    }
   
    public void addSupplier(String name, String address, String contact, long phoneNum)
    {
        
        suppliers.add(new Supplier(getLastSupplierId() + 1, name, address, contact, phoneNum));
    }
    
    public void removeSupplier(Supplier supplier)
    {
        suppliers.remove(supplier);
    }
    
    private long getLastSupplierId()
    {
         return suppliers.isEmpty() ? 0 : suppliers.get(suppliers.size()-1).getSupplierId(); 
         
    }
    public Supplier getSupplierById(long id)
    {
        return suppliers.stream().filter(x -> x.getSupplierId() == id).findFirst().orElse(null);
    }

        
    /****************************EMPLOYEE******************************/
    ///////////////////////////////////////////////////////////////////  
       
    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }
    
    public void setEmployees(ArrayList<Employee> newEmployyes)
    {
        this.employees = FXCollections.observableArrayList(newEmployyes);
    }
    
    public void addEmployee(Employee emp) {
        // Inserts a toy into the shop data model
        employees.add(emp);
    }
    
    
}
