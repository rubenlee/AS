package Commands;

import JPA.Cart;
import JPA.DiscountFacade;
import JPA.Product;
import Session.DataDump;
import Session.InactivityLog;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet(name = "EliminateDiscountCommand", urlPatterns = {"/EliminateDiscountCommand"})
public class EliminateDiscountCommand extends FrontCommand {

    DiscountFacade discountFacade = lookupDiscountFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    
    @Override
    public void process() {
        inactivityLog.Log("CartCommand", "Proccess");
        discountFacade.removeDiscount(Integer.parseInt(request.getParameter("id")));
        session.setAttribute("discounts", discountFacade.findAll());
        dataDump.setCart();
        inactivityLog.Log("Cart.jsp", "Pagina");
        forward("/ViewDiscount.jsp");
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

    private DiscountFacade lookupDiscountFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DiscountFacade) c.lookup("java:global/WebShop/DiscountFacade!JPA.DiscountFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}