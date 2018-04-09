package Commands;

import Session.Calculate;
import Session.Cart;
import Session.Disccount;
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

@WebServlet(name = "CartCommand", urlPatterns = {"/CartCommand"})
public class CartCommand extends FrontCommand {

    Calculate calculate = lookupCalculateBean();
    Disccount disccount = lookupDisccountBean();
    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    @EJB
    private Cart cart;
    
    
    @Override
    public void process() {
        inactivityLog.Log("CartCommand", "Proccess");
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        calculate = new Calculate();
        disccount = new Disccount();
        for (Item item : cart.getContents()){
            calculate.add(Integer.parseInt(item.getValue()));
        }
        if(request.getParameter("code") != null){
            int temp = disccount.remainder(calculate.getValue(),Integer.parseInt(request.getParameter("code")));
            calculate.setValue(temp);
        }
        session.setAttribute("total", calculate.getValue());
        try {
            inactivityLog.Log("Cart.jsp", "Pagina");
            forward("/Cart.jsp");
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

    private Disccount lookupDisccountBean() {
        try {
            Context c = new InitialContext();
            return (Disccount) c.lookup("java:global/WebShop/Disccount!Session.Disccount");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private Calculate lookupCalculateBean() {
        try {
            Context c = new InitialContext();
            return (Calculate) c.lookup("java:global/WebShop/Calculate!Session.Calculate");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}