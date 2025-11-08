
package classesDAO;

import classes.JPAUtil;
import jakarta.persistence.EntityManager;
import classes.FormaPagamento;

public class FormaPagamentoDAO {
    public void cadastrarVenda(FormaPagamento formaPagamento) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(formaPagamento.getVenda()); 
            em.persist(formaPagamento);             
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; 
        } finally {
            em.close();
        }
    }
    
    
}
