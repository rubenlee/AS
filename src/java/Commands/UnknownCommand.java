/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Usuario
 */
@WebServlet(name = "UnknownCommand", urlPatterns = {"/UnknownCommand"})
public class UnknownCommand extends FrontCommand {
    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    @Override
    public void process() {
        inactivityLog.Log("UnknownCommand", "process");
        dataDump.setUnknown();
        inactivityLog.Log("Unknown.jsp", "Pagina");
        forward("/Unknown.jsp");
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
