package Commands;

import JPA.Product;
import JPA.ProductFacade;
import java.util.ArrayList;
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
public class ResultOrderCommand extends FrontCommand {

    ProductFacade productFacade = lookupProductFacadeBean();

    @Override
    public void process() {
        session.setAttribute("results", productFacade.getProductOrderBy());
        forward("/Results.jsp");

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
