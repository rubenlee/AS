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
public class Item {
    private InactivityLog inactivityLog;
    private Save save;
    private String id;
    private String name;
    private String value;

    public Item() {
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
            save = InitialContext.doLookup("java:global/WebShop/Save!Session.Save");
        } catch (NamingException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getId() {
        inactivityLog.Log("Item", "getId");
        try {
            save.saveActions("Item::getId::Cogiendo el id");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void setId(String id) {
        inactivityLog.Log("Item", "setId");
        try {
            save.saveActions("Item::setId::Poniendo el id");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
    }

    public void setName(String name) {
        inactivityLog.Log("Item", "setName");
        try {
            save.saveActions("Item::setName::Poniendo el nombre");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name = name;
    }

    public void setValue(String value) {
        inactivityLog.Log("Item", "setValue");
        try {
            save.saveActions("Item::setValue::Poniendo el valor");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.value = value;
    }

    public String getName() {
        inactivityLog.Log("Item", "getName");
        try {
            save.saveActions("Item::getName:Cogiendo el nombre");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public String getValue() {
        inactivityLog.Log("Item", "getValue");
        try {
            save.saveActions("Item::getValue::Cogiendo el valor");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    public Item create(){
        inactivityLog.Log("Item", "create");
        try {
            save.saveActions("Item::create::Devolviendo el item");
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de Item");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de Item");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de Item");
    }
}
