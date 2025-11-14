
package controllers;

import classes.Produto;
import java.util.List;
import services.ProdutoService;

public class ProdutoController {
    private final ProdutoService produtoService;
    
    public ProdutoController() {
        this.produtoService = new ProdutoService();
    }
    
    public void cadastrarFuncionario(Produto produto) {
        try {
            produtoService.cadastrarProduto(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o produto.");
        }
    }
    
    public void atualizarFuncionario(Produto produto) {
        try {
            produtoService.editarProduto(produto);
            System.out.println("Dados do produto atualizados com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na atualização");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao atualizar o produto.");
        }
    }

    public void excluirFuncionario(String id) {
        try {
            produtoService.excluirProdutos(id);
            System.out.println("Produto removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na exclusão");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao excluir o produto.");
        }
    }
    
    public List<Produto> listarProdutos(String cod) {
        try {
            return produtoService.listarProdutos(cod);
        } catch (Exception e) {
            System.out.println("Falha ao listar os produtos cadastrados.");
            return null;
        }
    }
    
    public Produto buscarPorId(Long id) {
        try {
            return produtoService.buscarPorId(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na busca");
        } catch (Exception e) {
            System.out.println("Falha ao buscar o produto pelo ID.");
        }
        return null;
    }
}
