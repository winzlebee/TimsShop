/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimsShop.Models.DataModels;

import javafx.collections.ObservableList;

/**
 *
 * @author Alex
 */
public interface Storable<T>
{
   public void setData();
   public ObservableList<T> getStorage();
    
}
