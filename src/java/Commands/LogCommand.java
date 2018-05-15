package Commands;

import Session.Cart;
import Session.DataDump;
import Session.InactivityLog;
import Session.Item;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet(name = "LogCommand", urlPatterns = {"/LogCommand"})
public class LogCommand extends FrontCommand {

    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();
            
    public void process() {
        inactivityLog.Log("LogCommand", "Proccess");
        request.setAttribute("log", inactivityLog.getLog());
        inactivityLog.Log("Log.jsp", "Pagina");
        dataDump.setLog();
        forward("/Log.jsp");
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