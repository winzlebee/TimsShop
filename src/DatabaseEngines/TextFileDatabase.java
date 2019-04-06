/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEngines;

import TimsShop.Models.DataModels.ShopDataStorage;
import TimsShop.Models.ItemModels.Category;
import TimsShop.Models.ItemModels.Toy;
import TimsShop.Models.UserModels.Employee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class TextFileDatabase implements AbstractDatabase {
    boolean fileCreated = false;
    File databaseFile;
   
    public TextFileDatabase() {
        databaseFile = new File("database.tso");
    }

    @Override
    public void writeDataStorage(ShopDataStorage storage) {
        // Write all the elements of the ShopDataStorage to the output file we have specified
        
        // Output the toys
        try {
            databaseFile.delete();
            
            FileOutputStream outWrite = new FileOutputStream(databaseFile);
            ObjectOutputStream objectOutWrite = new ObjectOutputStream(outWrite);
            
            // Write all of the arrays into the output database in order
            objectOutWrite.writeObject(new ArrayList<Toy>(storage.getToys()));
            objectOutWrite.writeObject(new ArrayList<Category>(storage.getCategories()));
            objectOutWrite.writeObject(new ArrayList<Employee>(storage.getEmployees()));
            
            objectOutWrite.close();
            outWrite.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void readDataStorage(ShopDataStorage storage) {
        // Populate all the fields of the passed instance with the data storage elements that we need
        
        ArrayList<Toy> toysList = new ArrayList<>();
        ArrayList<Category> categoriesList = new ArrayList<>();
        ArrayList<Employee> employeeList = new ArrayList<>();
         
        try
        {
            FileInputStream fis = new FileInputStream(databaseFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            toysList = (ArrayList) ois.readObject();
            categoriesList = (ArrayList) ois.readObject();
            employeeList  = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        
        // Load the new array into the ShopDataStorage object
        storage.setCategories(categoriesList);
        storage.setToys(toysList);
        storage.setEmployees(employeeList);
         
        //Verify list data
        for (Toy toy : toysList) {
            System.out.printf("%s->%f", toy.getName(), toy.getPrice());
        }
        
    }

    @Override
    public boolean hasDataStorage() {
        return databaseFile.exists();
    }
    
}
