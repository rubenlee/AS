/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Session.InactivityLog;
import Session.Profile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ruben
 */
@WebServlet(name = "ProfileCommand", urlPatterns = {"/ProfileCommand"})
public class ProfileCommand extends FrontCommand {

    Profile profile = lookupProfileBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();

    @Override
    public void process() {
        request.setAttribute("name", profile.getName());
        request.setAttribute("email", profile.getEmail());
        request.setAttribute("adress", profile.getAdress());
        inactivityLog.Log("ProfileCommand", "process");
        try {
            inactivityLog.Log("Profile.jsp", "Pagina");
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
}
