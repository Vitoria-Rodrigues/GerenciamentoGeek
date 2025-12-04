
package classesDAO;

import classes.Cargo;
import classes.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CargoDAO {
    
    public List<Cargo> pegarCargos(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<Cargo> query = em.createQuery("SELECT c FROM Cargos c", Cargo.class);
            return query.getResultList();
        } catch(Exception e){
            System.out.println(e);
        } finally{
            em.close();
        }
        return null;
    }
    
    public Cargo buscarPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.find(Cargo.class, id);
        } catch(Exception e){
            System.out.println(e);
        } finally{
            em.close();
        }
        return null;
    }
}
