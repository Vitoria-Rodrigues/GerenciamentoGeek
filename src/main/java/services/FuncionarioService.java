
package services;

import DTO.FuncionarioDTO;
import classes.Cargo;
import classes.Funcionario;
import classes.Login;
import classesDAO.CargoDAO;
import classesDAO.FuncionarioDAO;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioDAO funcionarioDAO;
    private final CargoDAO cargoDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
        this.cargoDAO = new CargoDAO();
    }

    public void salvarFuncionario(FuncionarioDTO funcionarioDTO) {
        Login login = new Login(funcionarioDTO.getLogin(), funcionarioDTO.getSenha());
        Cargo cargo = cargoDAO.buscarPorId(funcionarioDTO.getCargo());
        
        Funcionario funcionario = new Funcionario(funcionarioDTO.getNome(), funcionarioDTO.getCpf(), funcionarioDTO.getLogradouro(), 
        funcionarioDTO.getCep(), funcionarioDTO.getNumero(), funcionarioDTO.getComplemento(), funcionarioDTO.getTelefone(), 
        login, cargo);
        
        funcionarioDAO.salvar(funcionario);
    }
    
    public void atualizarFuncionario(FuncionarioDTO funcionarioDTO, Long id) {
        Cargo cargo = cargoDAO.buscarPorId(funcionarioDTO.getCargo());
        
        Funcionario funcionarioEdit = funcionarioDAO.buscarPorId(id);
        funcionarioEdit.setNomeF(funcionarioDTO.getNome());
        funcionarioEdit.setCpfF(funcionarioDTO.getCpf());
        funcionarioEdit.setLogradouro(funcionarioDTO.getLogradouro());
        funcionarioEdit.setCep(funcionarioDTO.getCep());
        funcionarioEdit.setNumero(funcionarioDTO.getNumero());
        funcionarioEdit.setComplemento(funcionarioDTO.getComplemento());
        funcionarioEdit.setTelefoneF(funcionarioDTO.getTelefone());
        funcionarioEdit.getLogin().setSenha(funcionarioDTO.getSenha());
        funcionarioEdit.setCargo(cargo);
        
        funcionarioDAO.atualizar(funcionarioEdit);
    }
    
    public Funcionario buscarPorId(Long id) {
        return funcionarioDAO.buscarPorId(id);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    public List<Funcionario> buscarPorCPF(String cpf) {
        return funcionarioDAO.buscarPorCPF(cpf);
    }

    public void excluirFuncionario(String id) {
        funcionarioDAO.excluir(id);
    }
}
