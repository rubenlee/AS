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

@WebServlet(name = "StatisticCommand", urlPatterns = {"/StatisticCommand"})
public class StatisticCommand extends FrontCommand {

    DataDump dataDump = lookupDataDumpBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();


    @Override
    public void process() {
        inactivityLog.Log("StatisticCommand", "process");
        dataDump.setStatistic();
        request.setAttribute("login", dataDump.getLogin());
        request.setAttribute("logoff", dataDump.getLogoff());
        request.setAttribute("productsClicked", dataDump.getProductsClicked());
        request.setAttribute("frontServlet", dataDump.getFrontServlet());
        request.setAttribute("sessionServlet", dataDump.getSessionServlet());
        request.setAttribute("index", dataDump.getIndex());
        request.setAttribute("list", dataDump.getList());
        request.setAttribute("cart", dataDump.getCart());
        request.setAttribute("profile", dataDump.getProfile());
        request.setAttribute("Unknown", dataDump.getUnknown());
        request.setAttribute("statistic", dataDump.getStatistic());
        request.setAttribute("sign", dataDump.getSign());
        request.setAttribute("singletonAccess", dataDump.getTimeOut());
        inactivityLog.Log("Statistic.jsp", "Pagina");
        dataDump.setStatistic();
        forward("/Statistic.jsp");
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