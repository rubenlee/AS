package Commands;

import Session.DataDump;
import Session.InactivityLog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "IndexCommand", urlPatterns = {"/IndexCommand"})
public class IndexCommand extends FrontCommand {

    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    @Override
    public void process() {
        inactivityLog.Log("SessionServlet", "process");
        dataDump.setIndex();
        dataDump.setSessionServlet();
        inactivityLog.Log("Index.jsp", "Pagina");
        forward("/Index.jsp");
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