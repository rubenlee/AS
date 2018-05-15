/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users getUserByName(String name) {
        Query q = em.createNamedQuery("Users.findByName");
        q.setParameter("name", name);
        Users users = null;
        try {
            users = (Users) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return users;
    }
    
    public Users getUserById(int id) {
        Query q = em.createNamedQuery("Users.findById");
        q.setParameter("id", id);
        Users users = null;
        try {
            users = (Users) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return users;
    }
    
    public void updateUserName(String name, int id) {
        Query update = em.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
        update.setParameter(1, name);
        update.setParameter(2, id);
        update.executeUpdate();
    }
    
    public void updateUserCity(String city, int id){
        Query update = em.createNativeQuery("UPDATE users SET city = ? WHERE id = ?");
        update.setParameter(1, city);
        update.setParameter(2, id);
        update.executeUpdate();
    }
    
    public void updateUserPhone(String phone, int id){
        Query update = em.createNativeQuery("UPDATE users SET phone = ? WHERE id = ?");
        update.setParameter(1, phone);
        update.setParameter(2, id);
        update.executeUpdate();
    }
    
    public void updateUserPass(String pass, int id){
        Query update = em.createNativeQuery("UPDATE users SET password = ? WHERE id = ?");
        update.setParameter(1, pass);
        update.setParameter(2, id);
        update.executeUpdate();
    }
}
