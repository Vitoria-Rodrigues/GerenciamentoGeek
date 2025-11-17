
package DTO;

public class FuncionarioDTO {
    private String nome;
    private String cpf;
    private String logradouro;
    private String cep;
    private String telefone;
    private String numero;
    private String complemento;
    private Long cargo;

    public FuncionarioDTO(String nome, String cpf, String logradouro, String cep, String telefone, String numero, String complemento, Long cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.cep = cep;
        this.telefone = telefone;
        this.numero = numero;
        this.complemento = complemento;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }
    
}
