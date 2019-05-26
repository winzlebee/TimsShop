
package TimsShop.Models.RelationModels;

import TimsShop.Models.ItemModels.Item;
import TimsShop.Models.UserModels.Customer;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author win
 */
public class Sale implements Serializable {
    Date m_saleDate;
    
    // Data members for serialization
    final long m_customerId;
    
    // Relates item id to quantity of particular item
    Map<Long, Integer> m_quantities;
    
    /**
     *  Initialize a sale object with the particular items and their respective quantities.
     * @param quantities
     * @param customer
     */
    public Sale(Map<Item, Integer> quantities, Customer customer) {
        // Initialize new date at the current instant in time
        m_saleDate = new Date();
        
        m_customerId = customer.getUserID();
        
        m_quantities = new HashMap<>();
        quantities.forEach((item, quantity) -> m_quantities.put(item.getId(), quantity));
    }
    
    public Date getDate() {
        return m_saleDate;
    }
    
    public long getCustomerId() {
        return m_customerId;
    }
    
    public int getNumItems() {
        return m_quantities.size();
    }
    
    public int getTotalQuantity() {
        int total = 0;
        
        for (Long item : m_quantities.keySet()) {
            total += m_quantities.get(item);
        }
        
        return total;
    }
}
