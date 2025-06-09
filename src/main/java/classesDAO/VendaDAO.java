
package classesDAO;

import classes.Cliente;
import classes.JPAUtil;
import classes.Produto;
import classes.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import validacao.Alerta;

public class VendaDAO {

    public static Cliente listarCPF(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente cli;
        TypedQuery<Cliente> consulta;
        
        try {
                consulta = em.createQuery("SELECT c from Cliente c WHERE c.cpfC = :cpfC", Cliente.class);
                consulta.setParameter("cpfC", cpf);
                cli = consulta.getSingleResult();
                  
            return cli;
        } catch (Exception e) {
            Alerta.Erro("Erro listagem", "Erro as buscar o CPF");
        }
        return null;  
    }
    
    public static Produto listarProdutos(String cod) {
        EntityManager em = JPAUtil.getEntityManager();
        Produto prod;
        TypedQuery<Produto> consulta;
        
        try {
                consulta = em.createQuery("SELECT p from Produto p WHERE p.codigoProd = :codigoProd", Produto.class);
                consulta.setParameter("codigoProd", cod);
                prod = consulta.getSingleResult();
                  
            return prod;
        } catch (Exception e) {
            Alerta.Erro("Erro listagem", "Erro as buscar o Código");
        }
        return null;  
    }
    
    public static List<Venda> listarVendas(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Venda> listaVenda = new ArrayList<>();
        Query consulta;

        try {
            if (nome.equals("")) {
                consulta = em.createQuery("SELECT venda FROM Venda venda");
                listaVenda = consulta.getResultList();
            } else {
                consulta = em.createQuery("SELECT v from Venda v WHERE v.cliente.nomeC = :nomeC");
                consulta.setParameter("nomeC", nome);
                listaVenda = consulta.getResultList();
            }

            return listaVenda;
        } catch (Exception e) {
            Alerta.Erro("Erro listagem", "Erro as buscar informação para lista");
        }

        return listaVenda;
    }
    
    public static void excluirVendas(String id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Venda vendaRemover = em.find(Venda.class, id);
            em.remove(vendaRemover);
            em.getTransaction().commit();

        } catch (Exception e) {
            Alerta.Erro("Erro excluir", "Erro ao excluir a venda no banco");
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
