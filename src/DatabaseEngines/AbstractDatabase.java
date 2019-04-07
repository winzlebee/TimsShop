package DatabaseEngines;

import TimsShop.Models.DataModels.ShopDataStorage;

/**
 * @serial Responsible for serializing a particular datastore
 * @author win
 */
public interface AbstractDatabase {
    /**
     * Writes a ShopDataStorage straight to whatever attached storage method has been chosen.
     * @param storage The storage item to send to the database engine for writing
     */
    public void writeDataStorage(ShopDataStorage storage);
    
    /**
     * Reads data storage in from the database engine specified
     * @param storage Storage item to read into.
     */
    public void readDataStorage(ShopDataStorage storage);
    
    /**
     * If the data storage currently exists in the chosen engine
     * @return Whether the storage system exists or need to be created.
     */
    boolean hasDataStorage();
}
