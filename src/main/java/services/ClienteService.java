
package services;

import classes.Cliente;
import classesDAO.ClienteDAO;
import java.util.List;
import validacao.Alerta;


public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public void cadastrarCliente(Cliente cliente){
        try{
            clienteDAO.salvar(cliente);
            Alerta.Sucesso("Cadastro concluído!", "Cliente cadastrado com sucesso!");
        } catch(Exception e){
            Alerta.Erro("Erro no cadastro", "Erro ao inserir o cadastro no banco");
        }
    }
    
    public void editarCliente(Cliente cliente){
        try{
            clienteDAO.atualizar(cliente);
            Alerta.Sucesso("Sucesso!", "Edição realizar com sucesso!");
        } catch(Exception e){
            Alerta.Erro("Erro ao editar", "Ocorreu um erro ao editar as informações");
        }
    }
    
    public List<Cliente> listarClientes(String cpf){
        try{
            if(cpf == null || cpf.isEmpty()){
                return clienteDAO.listarTodos();
            }
            return clienteDAO.buscarPorCPF(cpf);
        }catch (Exception e){
            Alerta.Erro("Erro listagem", "Erro ao buscar informação para lista");
            return List.of();
        }
    }
    
    public Cliente buscarCliente(String idCliente){
        try{
            return clienteDAO.buscarPorId(idCliente);
        }catch(Exception e){
            Alerta.Erro("Erro", "Erro ao listar o cliente");
            return null;
        }
    }
    
    public void deletarCliente(String idCliente){
        try{
            clienteDAO.excluir(idCliente);
            Alerta.Sucesso("Sucesso!", "Cliente excluido com sucesso!");
        } catch(Exception e){
            Alerta.Erro("Erro na exclusão", "Erro ao excluir o cadastro no banco");
        }
    }
}































