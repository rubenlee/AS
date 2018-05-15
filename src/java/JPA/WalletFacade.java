/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
@Stateless
public class WalletFacade extends AbstractFacade<Wallet> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WalletFacade() {
        super(Wallet.class);
    }
    
    public Wallet getWalletByUserId(Users id) {
        Query q = em.createQuery("SELECT w FROM Wallet w WHERE w.iduser = :iduser");
        q.setParameter("iduser", id);
        return (Wallet) q.getSingleResult();
    }
    
    public void UpdateCuantityWallet(int cuantity, int id){
        Query update = em.createNativeQuery("UPDATE wallet SET cuantity = ? WHERE iduser = ?");
        update.setParameter(1, cuantity);
        update.setParameter(2, id);
        update.executeUpdate();
    }
}
