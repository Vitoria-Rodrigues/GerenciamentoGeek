package classesDAO;

import classes.Cliente;
import classes.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente){
        executarTransacao(em -> em.persist(cliente));
    }
    
    public void atualizar(Cliente cliente){
        executarTransacao(em -> em.merge(cliente));
    }
    
    public Cliente buscarPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.find(Cliente.class, id);
        } catch(Exception e){
            System.out.println(e);
        } finally{
            em.close();
        }
        return null;
    }
    
    public List<Cliente> listarTodos(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch(Exception e){
            System.out.println(e);
        }finally{
            em.close();
        }
        return null;
    }
    
    public Cliente buscarPorCPF(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpfC = :cpfC", Cliente.class);
            query.setParameter("cpfC", cpf);
            return query.getSingleResult();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            em.close();
        }
        return null;
    }
    
    public void excluir(String id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if(cliente != null){
            em.remove(cliente);
            }
        em.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            em.close();
        }
    }
    
    private void executarTransacao(java.util.function.Consumer<EntityManager> acao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            acao.accept(em);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
