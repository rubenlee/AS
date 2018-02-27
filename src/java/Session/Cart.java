package Session;

import java.util.List;
import javax.ejb.Remote;

/** 
 * @author Ruben
 */

@Remote
public interface Cart {
    public void initialize();
    public void addItem(Item item);
    public void removeItem(Item item);
    public List<Item> getContents();
    public void remove();
    public String getUser();
}
