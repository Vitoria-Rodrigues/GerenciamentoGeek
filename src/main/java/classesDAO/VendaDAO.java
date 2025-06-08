
package classesDAO;

import classes.JPAUtil;
import classes.Venda;
import jakarta.persistence.EntityManager;
import validacao.Alerta;

public class VendaDAO {
    public static void cadastrarVenda(Venda venda) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
            Alerta.Sucesso("Cadastro concluído!", "Venda cadastrado com sucesso!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            Alerta.Erro("Erro no cadastro", "Erro ao inserir o cadastro no banco");
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
