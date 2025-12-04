
package classesDAO;

import classes.Categoria;
import classes.JPAUtil;
import classes.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    public void cadastrarProduto(Produto produto) {
        executarTransacao(em -> em.persist(produto));
    }

    public void editarProduto(Produto produto) {
        executarTransacao(em -> em.merge(produto));
    }

    public void excluirProdutos(String id) {
        executarTransacao(em -> {
            Produto produtoRemover = em.find(Produto.class, id);
            if (produtoRemover != null) {
                em.remove(produtoRemover);
            }
        });
    }
    
    public Produto buscarPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.find(Produto.class, id);
        } catch(Exception e){
            System.out.println(e);
        } finally{
            em.close();
        }
        return null;
    }

    public List<Produto> listarProdutos(String cod) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> listaProd = new ArrayList<>();

        try {
            Query consulta;
            if (cod == null || cod.isEmpty()) {
                consulta = em.createQuery("SELECT p FROM Produto p", Produto.class);
            } else {
                consulta = em.createQuery("SELECT p FROM Produto p WHERE p.codigoProd = :codigoProd", Produto.class);
                consulta.setParameter("codigoProd", cod);
            }
            listaProd = consulta.getResultList();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            em.close();
        }

        return listaProd;
    }

    public List<Categoria> pegarCategoria() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Categoria> consulta = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
            return consulta.getResultList();
        } catch(Exception e){
            System.out.println(e);
        } finally {
            em.close();
        }
        return null;
    }

    private void executarTransacao(java.util.function.Consumer<EntityManager> acao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            acao.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
}
