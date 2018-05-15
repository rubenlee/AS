package Commands;


import JPA.Cart;
import JPA.Discount;
import JPA.DiscountFacade;
import Session.Calculate;
import Session.DataDump;
import Session.Disccount;
import Session.InactivityLog;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet(name = "CartCommand", urlPatterns = {"/CartCommand"})
public class DiscountCommand extends FrontCommand {

    Disccount disccount = lookupDisccountBean();

    DiscountFacade discountFacade = lookupDiscountFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    
    @Override
    public void process() {
        inactivityLog.Log("DiscountCommand", "Proccess");
        Discount discount = discountFacade.getDiscountByName(request.getParameter("code"));
        Cart cart = (Cart) session.getAttribute("cart");
        if(discount != null){
            int total = disccount.remainder(cart.getTotal(), Integer.parseInt(discount.getAmount()));
            session.setAttribute("total", total);
        }
        
        dataDump.setCart();
        inactivityLog.Log("Cart.jsp", "Pagina");
        forward("/Cart.jsp");
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

    private Disccount lookupDisccountBean() {
        try {
            Context c = new InitialContext();
            return (Disccount) c.lookup("java:global/WebShop/Disccount!Session.Disccount");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}