/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ruben
 */
@Stateless
@LocalBean
public class Disccount {
    private InactivityLog inactivityLog;
    private Save save;
    
    public Disccount(){
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
            save = InitialContext.doLookup("java:global/WebShop/Save!Session.Save");
        } catch (NamingException ex) {
            Logger.getLogger(Disccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int remainder(int a , int b) {
        inactivityLog.Log("Disccount", "remainder");
        try {
            save.saveActions("Disccount::remainder::Calcula la rebaja");
        } catch (IOException ex) {
            Logger.getLogger(Disccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        int temp = 0;
        if (b != 0) {
            temp = a - (a * b / 100);
        }
        return temp;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de Disccount");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de Disccount");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de Disccount");
    }
}
