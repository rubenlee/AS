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
public class DiscountFacade extends AbstractFacade<Discount> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscountFacade() {
        super(Discount.class);
    }

    public Discount getDiscountByName(String name) {
        Query q = em.createNamedQuery("Discount.findByName");
        q.setParameter("name", name);
        Discount discount = null;
        try {
            discount = (Discount) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return discount;
    }

    public void insertNew(String name, int value) {
        Query query = em.createNativeQuery("INSERT INTO discount (name, amount) VALUES (?, ?)");
        query.setParameter(1, name);
        query.setParameter(2, value);
        query.executeUpdate();
    }

    public void removeDiscount(int id) {
        Query q = em.createNativeQuery("DELETE FROM discount WHERE id = ?");
        q.setParameter(1, id);
        q.executeUpdate();
    }
}
