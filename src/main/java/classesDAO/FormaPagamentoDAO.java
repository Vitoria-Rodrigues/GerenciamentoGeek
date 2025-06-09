
package classesDAO;

import classes.JPAUtil;
import jakarta.persistence.EntityManager;
import classes.FormaPagamento;
import validacao.Alerta;

public class FormaPagamentoDAO {
    public static void cadastrarVenda(FormaPagamento formapagamento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(formapagamento.getVenda());
            em.persist(formapagamento);
            em.getTransaction().commit();
            Alerta.Sucesso("Cadastro concluído!", "Venda cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();    
            Alerta.Erro("Erro no cadastro", "Erro ao inserir o cadastro no banco");
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
