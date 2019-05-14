/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Models.DataModels;

import DatabaseEngines.AbstractDatabase;
import DatabaseEngines.TextFileDatabase;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Customer;
import TimsShop.Models.UserModels.Employee;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author win
 */
public class ShopDataStorage {
    private ObservableList<Toy> toys;
    private ObservableList<Category> categories;
    private ObservableList<Employee> employees;
    private ObservableList<Customer> customers;
    
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
    }
    
    private long getLastToyId() {
        if (toys.isEmpty()) return 0;
        return toys.get(toys.size()-1).getId();
    }
    
    private long getLastCategoryId() {
        return categories.isEmpty() ? 0 : categories.get(categories.size()-1).getID();
    }
    
    public void write() {
        // Write the data storage to the specified storage engine
        storageEngine.writeDataStorage(this);
    }
    
    public void insertToy(String name, float price, String description, long categoryId) {
        // Inserts a toy into the shop data model
        toys.add(new Toy(getLastToyId() + 1, name, price, categoryId, description));
    }
    
    public void addCategory(String name, ArrayList<String> tags) {
        // Inserts a toy into the shop data model
        categories.add(new Category(getLastCategoryId()+1, name, tags));
    }
    
    /**
     * Get a category by id. Returns null if not found.
     * @param id The id of the category to retrieve
     * @return Null if category not found.
     */
    public Category getCategoryById(long id) {
        return categories.stream().filter(x -> x.getID() == id).findFirst().orElse(null);
    }
        
    public void addEmployee(Employee emp) {
        // Inserts a toy into the shop data model
        employees.add(emp);
    }
    
    public void addCustomer(String firstName, String lastName, String email, long phoneNum, String dateOfJoining,  float storeCredit, boolean isMember, ArrayList<Long> interests)
    {
        ArrayList<String> iList = new ArrayList<>();
        
        for(Long l: interests)
        {
            iList.add(getCategoryById(l).getName());
        }
        
        customers.add(new Customer(firstName, lastName, email, phoneNum, dateOfJoining, storeCredit, isMember, iList));
    }

    
    
    /*************************GETTERS************************************/
     /////////////////////////////////////////////////////////////////////
    public ObservableList<Toy> getToys() {
        return toys;
    }
    
    public ObservableList<Category> getCategories() {
        return categories;
    }
    
    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }
    
    public ObservableList<Customer> getCustomers()
    {
        return customers;
    }
    
    /*************************SETTERS************************************/
    /////////////////////////////////////////////////////////////////////
    public void setCustomers(ArrayList<Customer> newCustomers)
    {
        this.customers = FXCollections.observableArrayList(newCustomers);
    }
    
    public void setToys(ArrayList<Toy> newToys) {
        this.toys = FXCollections.observableArrayList(newToys);
    }
    
    public void setCategories(ArrayList<Category> newCategories) {
        this.categories = FXCollections.observableArrayList(newCategories);
    }
    
    public void setEmployees(ArrayList<Employee> newEmployyes)
    {
        this.employees = FXCollections.observableArrayList(newEmployyes);
    }
    
}
