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
    }
    
    private long getLastToyId() {
        if (toys.isEmpty()) return 0;
        return toys.get(toys.size()-1).getId();
    }
    
    public void write() {
        // Write the data storage to the specified storage engine
        storageEngine.writeDataStorage(this);
    }
    
    public void insertToy(String name, float price, String description) {
        // Inserts a toy into the shop data model
        toys.add(new Toy(getLastToyId() + 1, name, price, new Category(), description));
    }
    
    public void addCategory(Category cat) {
        // Inserts a toy into the shop data model
        categories.add(cat);
    }
        
    public void addEmployee(Employee emp) {
        // Inserts a toy into the shop data model
        employees.add(emp);
    }
    
    public ObservableList<Toy> getToys() {
        return toys;
    }
    
    public ObservableList<Category> getCategories() {
        return categories;
    }
    
    public void setToys(ArrayList<Toy> newToys) {
        this.toys = FXCollections.observableArrayList(newToys);
    }
    
    public void setCategories(ArrayList<Category> newCategories) {
        this.categories = FXCollections.observableArrayList(newCategories);
    }
    
    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }
    
    public void setEmployees(ArrayList<Employee> newEmployyes)
    {
        this.employees = FXCollections.observableArrayList(newEmployyes);
    }
    
}
