package classesDAO;

import classes.JPAUtil;
import classes.Login;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class LoginDAO {
    
    public Login autenticar(String login, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Login> query = em.createQuery(
                "SELECT l FROM Login l WHERE l.login = :login AND l.senha = :senha",
                Login.class
            );
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
