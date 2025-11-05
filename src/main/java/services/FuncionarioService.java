
package services;

import classes.Funcionario;
import classesDAO.FuncionarioDAO;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public void salvarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo!");
        }

        else if (funcionario.getNomeF() == null || funcionario.getNomeF().isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório!");
        }

        else if (funcionario.getCpfF() == null || funcionario.getCpfF().isBlank()) {
            throw new IllegalArgumentException("O CPF do funcionário é obrigatório!");
        }
        
        else if (funcionario.getCep() == null || funcionario.getCep().isBlank()) {
            throw new IllegalArgumentException("O CEP do funcionário é obrigatório!");
        }
        
        else if (funcionario.getLogradouro() == null || funcionario.getLogradouro().isBlank()) {
            throw new IllegalArgumentException("O Logradouro do funcionário é obrigatório!");
        }
        
        else if (funcionario.getNumero() == null || funcionario.getNumero().isBlank()) {
            throw new IllegalArgumentException("O Numero da residencia do funcionário é obrigatório!");
        }
        
        else if (funcionario.getComplemento() == null || funcionario.getComplemento().isBlank()) {
            throw new IllegalArgumentException("O complemento do funcionário é obrigatório!");
        }
        
        else if (funcionario.getTelefoneF()== null || funcionario.getTelefoneF().isBlank()) {
            throw new IllegalArgumentException("O complemento do funcionário é obrigatório!");
        }

        funcionarioDAO.salvar(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        if (funcionario == null || funcionario.getId() == null) {
            throw new IllegalArgumentException("Funcionário inválido para atualização!");
        }

        funcionarioDAO.atualizar(funcionario);
    }

    public Funcionario buscarPorId(String id) {
        return funcionarioDAO.buscarPorId(id);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    public List<Funcionario> buscarPorCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode estar vazio!");
        }
        return funcionarioDAO.buscarPorCPF(cpf);
    }

    public void excluirFuncionario(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID do funcionário inválido!");
        }

        funcionarioDAO.excluir(id);
    }
}
