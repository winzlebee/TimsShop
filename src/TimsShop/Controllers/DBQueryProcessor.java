
package TimsShop.Controllers;

import TimsShop.Models.DataModels.ShopDataStorage;

/***********************
 *  DBQueryProcessor separates  the storage functionality 
    from applicationController to allow for a 
    more cohesive design in allowing for specialized object types to only deal
 * with their own concerns
 */
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
