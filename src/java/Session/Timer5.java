/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ruben
 */
@Singleton
@LocalBean
public class Timer5 {

    private String Old = "";
    private String New = "";
    
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void imprimirMensajeCincoSegundosOcioso() {
        InactivityLog inactivo = lookupInactivityLogBean();
        New = inactivo.getLog();
        if (!New.equals(Old)) {
            Old = inactivo.getLog();
        } else {
            inactivo.Log("No se ha realizado nada", "En 5 seg");
        }
    }

    private InactivityLog lookupInactivityLogBean() {
        try {
            Context c = new InitialContext();
            return (InactivityLog) c.lookup("java:global/WebShop/InactivityLog!Session.InactivityLog");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
