/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Models.DataModels;

import DatabaseEngines.AbstractDatabase;
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
        if (storageEngine.hasDataStorage()) storageEngine.readDataStorage(this);
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
