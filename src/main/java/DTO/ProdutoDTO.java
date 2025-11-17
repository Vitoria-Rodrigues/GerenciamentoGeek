
package DTO;

public class ProdutoDTO {
    private String nome;
    private String desc;
    private Double preco;
    private int qtdEstoque;
    private int codigo;

    public ProdutoDTO(String nome, String desc, Double preco, int qtdEstoque, int codigo) {
        this.nome = nome;
        this.desc = desc;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
