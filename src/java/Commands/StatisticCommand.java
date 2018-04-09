package Commands;

import Session.Cart;
import Session.DataDump;
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


    @Override
    public void process() {
        DataDump data = lookupDataDumpBean();
        request.setAttribute("login", data.getLogin());
        request.setAttribute("logoff", data.getLogin());
        request.setAttribute("productsClicked", data.getProductsClicked());
        request.setAttribute("frontServlet", data.getFrontServlet());
        request.setAttribute("sessionServlet", data.getSessionServlet());
        request.setAttribute("index", data.getIndex());
        request.setAttribute("list", data.getList());
        request.setAttribute("cart", data.getCart());
        request.setAttribute("profile", data.getProfile());
        request.setAttribute("Unknown", data.getUnknown());
        request.setAttribute("singletonAccess", data.getTimeOut());
        try {
            forward("/Statistic.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
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