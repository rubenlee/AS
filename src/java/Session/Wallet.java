package Session;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Wallet {
    
    private int amount = 0;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
