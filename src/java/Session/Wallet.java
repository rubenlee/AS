package Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class Wallet {
    private InactivityLog inactivityLog;
    private Save save;
    private int amount;
    
    public Wallet(){
        amount = 0;
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
            save = InitialContext.doLookup("java:global/WebShop/Save!Session.Save");
        } catch (NamingException ex) {
            Logger.getLogger(Wallet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAmount() {
        inactivityLog.Log("Wallet", "getAmount");
        try {
            save.saveActions("Wallet::getAmount::Cogiendo la cantidad");
        } catch (IOException ex) {
            Logger.getLogger(Wallet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public void setAmount(int amount) {
        inactivityLog.Log("Wallet", "setAmount");
        try {
            save.saveActions("Wallet::setAmount::Poniendo la cantidad");
        } catch (IOException ex) {
            Logger.getLogger(Wallet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.amount = amount;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de Wallet");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de Wallet");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de Wallet");
    }
}
