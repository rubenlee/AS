package Commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Usuario
 */
public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
    }
    
    abstract public void process();
    
    public void forward(String target){
        RequestDispatcher dp = context.getRequestDispatcher(target);
        try {
            dp.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
