package Commands;

import Session.Cart;
import Session.Item;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProfileCommand", urlPatterns = {"/ProfileCommand"})
public class CartCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        int sum = 0;
        for (Item item : cart.getContents()){
            sum += Integer.parseInt(item.getValue());
        }
        session.setAttribute("total", sum);
        try {
            forward("/Cart.jsp");
        } catch (ServletException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}