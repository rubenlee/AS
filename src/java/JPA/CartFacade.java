/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.util.List;
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
public class CartFacade extends AbstractFacade<Cart> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }
    
    public Cart getCartByUserId(Users id) {
        Query q = em.createQuery("SELECT c FROM Cart c WHERE c.iduser = :iduser");
        q.setParameter("iduser", id);
        return (Cart) q.getSingleResult();
    }
    
    public void UpdateContainSizeTotalCart(String contain, int total,int size, int id){
        Query update = em.createNativeQuery("UPDATE cart SET contain = ?, total = ?, size = ? WHERE iduser = ?");
        update.setParameter(1, contain);
        update.setParameter(2, total);
        update.setParameter(3, size);
        update.setParameter(4, id);
        update.executeUpdate();
    }
}
