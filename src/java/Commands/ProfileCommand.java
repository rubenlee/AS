/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Session.DataDump;
import Session.InactivityLog;
import Session.Profile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author ruben
 */
@WebServlet(name = "ProfileCommand", urlPatterns = {"/ProfileCommand"})
public class ProfileCommand extends FrontCommand {

            
    Profile profile = lookupProfileBean();
    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();

    @Override
    public void process() {
        /*if(Test.getName() != null) request.setAttribute("name", Test.getName());
        if(Test.getEmail()!= null) request.setAttribute("email", Test.getEmail());
        if(Test.getAdress()!= null) request.setAttribute("adress", Test.getAdress());*/
        inactivityLog.Log("ProfileCommand", "process");
        try {
            inactivityLog.Log("Profile.jsp", "Pagina");
            dataDump.setProfile();
            forward("/Profile.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
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

    private Profile lookupProfileBean() {
        try {
            Context c = new InitialContext();
            return (Profile) c.lookup("java:global/WebShop/Profile!Session.Profile");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DataDump lookupDataDumpBean() {
        try {
            Context c = new InitialContext();
            return (DataDump) c.lookup("java:global/WebShop/DataDump!Session.DataDump");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
