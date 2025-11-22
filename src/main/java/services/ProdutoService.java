
package services;

import DTO.ProdutoDTO;
import classes.Categoria;
import classes.Produto;
import classesDAO.CategoriaDAO;
import classesDAO.ProdutoDAO;
import java.util.List;

public class ProdutoService {
    private final ProdutoDAO produtoDAO;
    private final CategoriaDAO categoriaDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
        this.categoriaDAO = new CategoriaDAO();
    }

    public void cadastrarProduto(ProdutoDTO produtoDTO) {
        Categoria categoria = categoriaDAO.buscarPorId(produtoDTO.getCategoria());
        
        Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getDesc(), produtoDTO.getPreco(),
        produtoDTO.getQtdEstoque(), produtoDTO.getCodigo(), categoria);
        produtoDAO.cadastrarProduto(produto);
    }

    public void editarProduto(ProdutoDTO produtoDTO, Long id) {
        Categoria categoria = categoriaDAO.buscarPorId(produtoDTO.getCategoria());
        
        Produto produtoEdit = produtoDAO.buscarPorId(id);
        produtoEdit.setNomeProd(produtoDTO.getNome());
        produtoEdit.setDescProd(produtoDTO.getDesc());
        produtoEdit.setPreco(produtoDTO.getPreco());
        produtoEdit.setQtdEstoque(produtoDTO.getQtdEstoque());
        produtoEdit.setCodigoProd(produtoDTO.getCodigo());
        produtoEdit.setCategoria(categoria);
        
        produtoDAO.editarProduto(produtoEdit);
    }

    public void excluirProdutos(String id) { 
        produtoDAO.excluirProdutos(id);
    }
    
    public Produto buscarPorId(Long idProduto) {
        return produtoDAO.buscarPorId(idProduto);
    }

    public List<Produto> listarProdutos(String cod) {
        return produtoDAO.listarProdutos(cod);
    }

    public List<Categoria> pegarCategoria() {
        return produtoDAO.pegarCategoria();
    }
}
