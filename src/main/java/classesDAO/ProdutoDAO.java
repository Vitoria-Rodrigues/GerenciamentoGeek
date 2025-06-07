
package classesDAO;

import classes.Categoria;
import classes.JPAUtil;
import classes.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import validacao.Alerta;

public class ProdutoDAO {
     public static List<Categoria> pegarCategoria(){
        EntityManager em = JPAUtil.getEntityManager();
        List<Categoria> categ = new ArrayList<>();
        try{
            Query consulta = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
            categ = consulta.getResultList();
            return categ;
        } catch(Exception e){
             Alerta.Erro("Erro de consulta", "Erro ao consultar os cargos");
    }
        return categ;
  }
    public static void cadastrarProduto(Produto produto){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
            Alerta.Sucesso("Produto cadastrado com sucesso!", "Cadastro concluído!");            
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            Alerta.Erro("Erro ao inserir o cadastro no banco", "Erro no cadastro");
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    public static List<Produto> listarProdutos(String cod) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> listaProd = new ArrayList<>();
        Query consulta;

        try {
            if (cod.equals("")) {
                consulta = em.createQuery("SELECT produto FROM Produto produto");
                listaProd = consulta.getResultList();
            } else {
                consulta = em.createQuery("SELECT p from Produto p WHERE p.codigoProd = :codigoProd");
                consulta.setParameter("codigoProd", cod);
                listaProd = consulta.getResultList();
            }

            return listaProd;
        } catch (Exception e) {
            Alerta.Erro("Erro listagem", "Erro as buscar informação para lista");
        }

        return listaProd;
    }

    public static Produto listarProduto(String idProduto) {
        EntityManager em = JPAUtil.getEntityManager();
        Produto produto = null;

        try {
            produto = em.find(Produto.class, idProduto);
            return produto;
        } catch (Exception e) {
            Alerta.Erro("Erro", "Erro ao listar o produto.");
        } finally {
            em.close();
        }
        return produto;
    }
    
}
