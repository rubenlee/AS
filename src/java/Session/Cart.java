package Session;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;

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

    public void setActive() {
        active = !active;
    }

    public boolean isActive() {
        return active;
    }
    
    public void initialize() {
        list = new ArrayList<Item>();
        setActive();
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
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de cart");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de cart");
        if(active = true){
            System.out.println("Inicializado con exito");
        }
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Cerrando");
        active = false;
    }
}
