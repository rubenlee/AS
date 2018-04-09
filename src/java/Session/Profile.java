package Session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;

/**
 *
 * @author ruben
 */
@Stateful
@LocalBean
public class Profile {

    private String name = "a";
    private String email = "b";
    private String adress = "c";
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Inicializado el EJB de Profile");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Inicializado el EJB de Profile");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("Se cerro el EJB de Profile");
    }
}
