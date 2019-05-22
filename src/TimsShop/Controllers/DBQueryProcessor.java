
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;


public class DBQueryProcessor
{
    private ShopDataStorage storage;


    public DBQueryProcessor()
    {
        this.storage = new ShopDataStorage();
 
    }
    
    public void writeToStorage()
    {
        storage.write();
    }
    
    public ShopDataStorage getStorage()
    {
        return storage;
    }
    
    
}
