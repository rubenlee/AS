/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import JPA.Users;
import JPA.UsersFacade;
import Session.DataDump;
import Session.InactivityLog;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author ruben
 */
@WebServlet(name = "ProfileEditCommand", urlPatterns = {"/ProfileEditCommand"})
public class ProfileEditCommand extends FrontCommand {

    InactivityLog inactivityLog = lookupInactivityLogBean();

    UsersFacade usersFacade = lookupUsersFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    @Override
    public void process() {

        inactivityLog.Log("ProfileCommand", "process");
        inactivityLog.Log("Profile.jsp", "Pagina");
        dataDump.setProfile();
        updateDB();
        forward("/Index.jsp");
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

    private UsersFacade lookupUsersFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UsersFacade) c.lookup("java:global/WebShop/UsersFacade!JPA.UsersFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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

    private void updateDB() {
        Users user = (Users) session.getAttribute("user");
        String name = request.getParameter("username");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("pass");
        if (name != null) {
            if (!user.getName().equals(name)) {
                usersFacade.updateUserName(name, user.getId());
                user.setName(name);
            }
        }
        if (city != null) {
            if (user.getCity() == null || !user.getCity().equals(city)) {
                usersFacade.updateUserCity(city, user.getId());
                user.setCity(city);
            }
        }
        if (phone != null) {
            if (user.getPhone() == null || !user.getPhone().equals(phone)) {
                usersFacade.updateUserPhone(phone, user.getId());
                user.setPhone(phone);
            }
        }
        if (pass != "") {
            if (!user.getPassword().equals(pass)) {
                usersFacade.updateUserPass(pass, user.getId());
            }
        }
    }
}
