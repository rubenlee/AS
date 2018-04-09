package Commands;

import Session.Cart;
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

    InactivityLog inactivityLog = lookupInactivityLogBean();
    @EJB
    private Cart cart;
    
    @Override
    public void process() {
        inactivityLog.Log("CartCommand", "Proccess");
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        int sum = 0;
        for (Item item : cart.getContents()){
            sum += Integer.parseInt(item.getValue());
        }
        session.setAttribute("total", sum);
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

}