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
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Save {

    private InactivityLog inactivityLog;
        
    public Save(){
        try {
            inactivityLog = InitialContext.doLookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
        } catch (NamingException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveActions(String texto) throws IOException {
        File file = new File("C:\\Users\\ruben\\Desktop\\Actions log.txt");
        BufferedWriter output = null;
        output = new BufferedWriter(new FileWriter(file, true));
        output.write(texto);
        output.newLine();
        output.close();
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de Save");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de Save");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de Save");
    }
}
