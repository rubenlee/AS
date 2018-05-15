package Commands;


import JPA.Cart;
import JPA.CartFacade;
import JPA.Users;
import JPA.Wallet;
import JPA.WalletFacade;
import Session.DataDump;
import Session.InactivityLog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet(name = "AddCommand", urlPatterns = {"/AddCommand"})
public class AddCommand extends FrontCommand {

    CartFacade cartFacade = lookupCartFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    
    @Override
    public void process() {
        inactivityLog.Log("DiscountCommand", "Proccess");
        Cart cart = (Cart) session.getAttribute("cart");
        Users user = (Users) session.getAttribute("user");
        String temp = "";
        if(cart.getSize() == 0){
            temp = request.getParameter("id");
        }else{
            temp = cart.getContain() + "," + request.getParameter("id");
        }
        int temp2 = (cart.getTotal() + Integer.parseInt(request.getParameter("price")));
        cartFacade.UpdateContainSizeTotalCart(temp, temp2,cart.getSize()+1, user.getId());
        cart.setTotal(temp2);
        cart.setContain(temp);
        cart.setSize(cart.getSize()+1);
        dataDump.setCart();
        inactivityLog.Log("Cart.jsp", "Pagina");
        forward("/List.jsp");
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

    private WalletFacade lookupWalletFacadeBean() {
        try {
            Context c = new InitialContext();
            return (WalletFacade) c.lookup("java:global/WebShop/WalletFacade!JPA.WalletFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}