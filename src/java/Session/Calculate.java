/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
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
public class Calculate {
    private InactivityLog inactivityLog;
    private Save save;
    private int value;
    
    public Calculate() {
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
            save = InitialContext.doLookup("java:global/WebShop/Save!Session.Save");
            
        } catch (NamingException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
        value = 0;
    }

    public void setValue(int value) {
        inactivityLog.Log("Calculate", "setValue");
        this.value = value;
        try {
            save.saveActions("Calculate::setValue::Poniendo su valor");
        } catch (IOException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getValue() {
        inactivityLog.Log("Calculate", "getValue");
        try {
            save.saveActions("Calculate::setValue::Devolviendo su valor");
        } catch (IOException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public void add(int a) {
        inactivityLog.Log("Calculate", "add");
        value += a;
        try {
            save.saveActions("Calculate::setValue::Sumando");
        } catch (IOException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(int a) {
        inactivityLog.Log("Calculate", "remove");
        value -= a;
        try {
            save.saveActions("Calculate::setValue::Restando");
        } catch (IOException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de calculate");
    }

    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de calculate");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de calculate");
    }
}
