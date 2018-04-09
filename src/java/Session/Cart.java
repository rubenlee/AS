package Session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Cart {

    private List<Item> list;
    private String user;
    private boolean active = false;

    public boolean isActive() {
        return active;
    }
    
    public void initialize() {
        list = new ArrayList<Item>();
        active = true;
    }

    public void addItem(Item item) {
        list.add(item);
    }

    public void removeItem(Item item){
        int temp = 0;
        for(Item cart : list){
            if(cart.getId().equals(item.getId())){
                list.remove(temp);
                break;
            }
            temp++;
        }
    }

    public List<Item> getContents() {
        return list;
    }

    public void remove() {
        if(!list.isEmpty()){
            list = new ArrayList<Item>();
        }
    }
    
    public String getUser(){
        return user;
    }
}
