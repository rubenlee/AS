/*


Esta clase permite hacer el login.

OJO Hay que observar que estamos usando el email para el login puesto que es único, unique key.
Además encriptamos la contraseña.


 */
package Commands;


import JPA.Cart;
import JPA.CartFacade;
import JPA.Users;
import JPA.UsersFacade;
import JPA.Wallet;
import JPA.WalletFacade;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author ruben
 */
public class LoginCommand extends FrontCommand {

    CartFacade cartFacade = lookupCartFacadeBean();

    WalletFacade walletFacade = lookupWalletFacadeBean();

    UsersFacade usersFacade = lookupUsersFacadeBean();

    @Override
    public void process() {
        Users user = usersFacade.getUserByName(request.getParameter("username"));
        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            Cart cart = cartFacade.getCartByUserId(user);
            Wallet wallet = walletFacade.getWalletByUserId(user);
            session.setAttribute("user", user);
            session.setAttribute("cart", cart);
            session.setAttribute("wallet", wallet);
            forward("/Index.jsp");
        } else {
            forward("/Sign.jsp");
        }
    }

    private UsersFacade lookupUsersFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UsersFacade) c.lookup("java:global/WebShop/UsersFacade!JPA.UsersFacade");
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

    private CartFacade lookupCartFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CartFacade) c.lookup("java:global/WebShop/CartFacade!JPA.CartFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

}
