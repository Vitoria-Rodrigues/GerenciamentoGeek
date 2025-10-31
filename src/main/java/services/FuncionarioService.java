
package services;

import classes.Cargo;
import classes.Funcionario;
import classesDAO.FuncionarioDAO;
import java.util.List;
import validacao.Alerta;

public class FuncionarioService {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public void cadastrarFuncionario(Funcionario funcionario){
        try{
            funcionarioDAO.salvar(funcionario);
            Alerta.Sucesso("Cadastro concluído!", "Funcionario cadastrado com sucesso!");
        } catch(Exception e){
            Alerta.Erro("Erro no cadastro", "Erro ao inserir o cadastro no banco");
        }
    }
    
    public void editarCliente(Funcionario funcionario){
        try{
            funcionarioDAO.atualizar(funcionario);
            Alerta.Sucesso("Sucesso!", "Edição realizar com sucesso!");
        } catch(Exception e){
            Alerta.Erro("Erro ao editar", "Ocorreu um erro ao editar as informações");
        }
    }
    
    public List<Cargo> listarCargo(String cpf){
        try{
            return funcionarioDAO.listarCargos();
        }catch (Exception e){
            Alerta.Erro("Erro listagem", "Erro ao buscar informação para lista");
            return List.of();
        }
    }
    
    public List<Funcionario> listarFuncionarios(String cpf){
        try{
            if(cpf == null || cpf.isEmpty()){
                return funcionarioDAO.listarTodos();
            }
            return funcionarioDAO.buscarPorCPF(cpf);
        }catch (Exception e){
            Alerta.Erro("Erro listagem", "Erro ao buscar informação para lista");
            return List.of();
        }
    }
    
    public Funcionario buscarFuncionario(String idFuncionario){
        try{
            return funcionarioDAO.buscarPorId(idFuncionario);
        }catch(Exception e){
            Alerta.Erro("Erro", "Erro ao listar o funcionario");
            return null;
        }
    }
    
}
