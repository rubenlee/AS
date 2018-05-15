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
public class ResultCommand extends FrontCommand {

    ProductFacade productFacade = lookupProductFacadeBean();



    @Override
    public void process() {
        String search = request.getParameter("search");
        if (search == null) {
            forward("/Index.jsp");
        } else {
            List<Product> product = new ArrayList<>();
            getProduct(product, search);
            if (product.isEmpty()) {
                for (String searchs : search.split("\\W")) {
                    getProduct(product, searchs);
                }
            }
            session.setAttribute("results", product);
            forward("/Results.jsp");
        }
    }

    private void getProduct(List<Product> product, String searchs) {
        List<Product> artworksTemp = productFacade.getSearchProduct(searchs);
        for (Product temp : artworksTemp) {
            if (!product.contains(temp)) {
                product.add(temp);
            }
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
