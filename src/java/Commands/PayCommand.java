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

@WebServlet(name = "PayCommand", urlPatterns = {"/PayCommand"})
public class PayCommand extends FrontCommand {

    WalletFacade walletFacade = lookupWalletFacadeBean();

    CartFacade cartFacade = lookupCartFacadeBean();

    DataDump dataDump = lookupDataDumpBean();

    InactivityLog inactivityLog = lookupInactivityLogBean();
    
    
    
    @Override
    public void process() {
        inactivityLog.Log("DiscountCommand", "Proccess");
        Cart cart = (Cart) session.getAttribute("cart");
        Wallet wallet = (Wallet) session.getAttribute("wallet");
        Users user = (Users) session.getAttribute("user");
        int total = (int) session.getAttribute("total");
        int temp = wallet.getCuantity() - total;
        walletFacade.UpdateCuantityWallet(temp,user.getId());
        cartFacade.UpdateContainSizeTotalCart("", 0,0, user.getId());
        wallet.setCuantity(temp);
        cart.setTotal(0);
        cart.setContain("");
        cart.setSize(0);
        dataDump.setCart();
        inactivityLog.Log("Cart.jsp", "Pagina");
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