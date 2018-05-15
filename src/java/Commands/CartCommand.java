package Commands;

import JPA.Cart;
import JPA.CartFacade;
import JPA.Product;
import JPA.ProductFacade;
import Session.Calculate;
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

@WebServlet(name = "CartCommand", urlPatterns = {"/CartCommand"})
public class CartCommand extends FrontCommand {

    ProductFacade productFacade = lookupProductFacadeBean();

    CartFacade cartFacade = lookupCartFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();

    @Override
    public void process() {
        inactivityLog.Log("CartCommand", "Proccess");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getSize() != 0) {
            List<Product> product = new ArrayList();
            for (String item : cart.getContain().split(",")) {
                product.add(productFacade.getProductById(Integer.parseInt(item)));
            }
            session.setAttribute("list", product);
            
        }
        session.setAttribute("total", cart.getTotal());
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

    private CartFacade lookupCartFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CartFacade) c.lookup("java:global/WebShop/CartFacade!JPA.CartFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProductFacade lookupProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProductFacade) c.lookup("java:global/WebShop/ProductFacade!JPA.ProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
