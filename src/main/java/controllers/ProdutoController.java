
package controllers;

import classes.Produto;
import java.util.List;
import services.ProdutoService;
import validacao.Alerta;

public class ProdutoController {
    private final ProdutoService produtoService;
    
    public ProdutoController() {
        this.produtoService = new ProdutoService();
    }
    
    public void cadastrarFuncionario(Produto produto) {
        try {
            produtoService.cadastrarProduto(produto);
            Alerta.Sucesso("Cadastro realizado com sucesso!", "Produto cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            Alerta.Erro("Erro no cadastro", e.getMessage());
        } catch (Exception e) {
            Alerta.Erro("Erro inesperado", "Ocorreu um erro ao cadastrar o produto.");
        }
    }
    
    public void atualizarFuncionario(Produto produto) {
        try {
            produtoService.editarProduto(produto);
            Alerta.Sucesso("Atualização concluída", "Dados do produto atualizados com sucesso!");
        } catch (IllegalArgumentException e) {
            Alerta.Erro("Erro na atualização", e.getMessage());
        } catch (Exception e) {
            Alerta.Erro("Erro inesperado", "Ocorreu um erro ao atualizar o produto.");
        }
    }

    public void excluirFuncionario(String id) {
        try {
            produtoService.excluirProdutos(id);
            Alerta.Sucesso("Exclusão concluída", "Produto removido com sucesso!");
        } catch (IllegalArgumentException e) {
            Alerta.Erro("Erro na exclusão", e.getMessage());
        } catch (Exception e) {
            Alerta.Erro("Erro inesperado", "Ocorreu um erro ao excluir o produto.");
        }
    }
    
    public List<Produto> listarProdutos(String cod) {
        try {
            return produtoService.listarProdutos(cod);
        } catch (Exception e) {
            Alerta.Erro("Erro na listagem", "Falha ao listar os produtos cadastrados.");
            return null;
        }
        }
    
    public Produto buscarPorId(Long id) {
        try {
            return produtoService.buscarPorId(id);
        } catch (IllegalArgumentException e) {
            Alerta.Erro("Erro na busca", e.getMessage());
        } catch (Exception e) {
            Alerta.Erro("Erro inesperado", "Falha ao buscar o produto pelo ID.");
        }
        return null;
    }
}
