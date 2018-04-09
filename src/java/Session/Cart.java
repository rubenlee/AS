package Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Cart {

    private InactivityLog inactivityLog;
    private Save save;
    private List<Item> list;
    private String user;
    private boolean active;
    
    public Cart(){
        active = false;
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
            save = InitialContext.doLookup("java:global/WebShop/Save!Session.Save");
        } catch (NamingException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setActive() {
        inactivityLog.Log("Cart", "setActive");
        active = !active;
        try {
            save.saveActions("Cart::setActive::Activando carrito");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isActive() {
        inactivityLog.Log("Cart", "isActive");
        try {
            save.saveActions("Cart::setValue::Comprobando carrito");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return active;
        
    }
    
    public void initialize() {
        inactivityLog.Log("Cart", "initialize");
        list = new ArrayList<Item>();
        setActive();
        try {
            save.saveActions("Cart::setValue::Inicializando carrito");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addItem(Item item) {
        inactivityLog.Log("Cart", "addItem");
        list.add(item);
        try {
            save.saveActions("Cart::setValue::Añadiendo objeto");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeItem(Item item){
        inactivityLog.Log("Cart", "removeItem");
        int temp = 0;
        for(Item cart : list){
            if(cart.getId().equals(item.getId())){
                list.remove(temp);
                break;
            }
            temp++;
        }
        try {
            save.saveActions("Cart::setValue::Removiendo objeto");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Item> getContents() {
        inactivityLog.Log("Cart", "getContents");
        try {
            save.saveActions("Cart::setValue::conseguiendo el contenido");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

    public void remove() {
        inactivityLog.Log("Cart", "remove");
        if(!list.isEmpty()){
            list = new ArrayList<Item>();
        }
        try {
            save.saveActions("Cart::setValue::Eliminandolo");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUser(){
        inactivityLog.Log("Cart", "getUser");
        try {
            save.saveActions("Cart::setValue::Conseguir usuario");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("Se cerro el EJB de cart");
        active = false;
    }
}
