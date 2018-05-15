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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ruben
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public Product getProductById(int id) {
        Query q = em.createNamedQuery("Product.findById");
        q.setParameter("id", id);
        Product product = null;
        try {
            product = (Product) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return product;
    }

    public List<Product> getProducts(int start) {
        Query q = em.createNamedQuery("Product.findAll");
        q.setMaxResults(3);
        q.setFirstResult(start);
        return q.getResultList();
    }
    
    public void removeProduct(String contain, int id) {
        Query q = em.createNativeQuery("UPDATE product SET contain = ? WHERE id = ?");
        q.setParameter(1, contain);
        q.setParameter(2, id);
        q.executeUpdate();
    }
    
    public List<Product> getSearchProduct(String name){
        Query q = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name");
        q.setParameter("name", "%"+name+"%");
        return q.getResultList();
    }
    
    public List<Product> getProductOrderBy() {
        Query q = em.createQuery("SELECT p FROM Product p ORDER BY p.price ASC");
        return q.getResultList();
    }
    
    public List<Product> searchWithAPI(String search){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        Predicate predicate = criteriaBuilder.like(product.<String>get("name"), "%"+search+"%");
        cq.where(predicate);
        cq.select(product).orderBy(criteriaBuilder.desc(product.get("price")));
        TypedQuery query = em.createQuery(cq);
        List<Product> result = query.getResultList();
        return result;
    } 
}
