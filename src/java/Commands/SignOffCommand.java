/*


Esta clase permite hacer el login.

OJO Hay que observar que estamos usando el email para el login puesto que es único, unique key.
Además encriptamos la contraseña.


 */
package Commands;


import JPA.Users;
import JPA.UsersFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author ruben
 */
public class SignOffCommand extends FrontCommand {

    UsersFacade usersFacade = lookupUsersFacadeBean();

    @Override
    public void process() {
        session.removeAttribute("user");
        session.removeAttribute("wallet");
        session.removeAttribute("cart");
        forward("/Index.jsp");
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
    

}
