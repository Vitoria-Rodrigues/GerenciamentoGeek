
package services;

import DTO.ClienteDTO;
import classes.Cliente;
import classesDAO.ClienteDAO;
import java.util.List;


public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public void salvarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getSexo(), clienteDTO.getTelefone());
        
        clienteDAO.salvar(cliente);
    }

    
    public void editarCliente(ClienteDTO clienteDTO, Long id) { 
        Cliente clienteEditar = clienteDAO.buscarPorId(id);
        clienteEditar.setNomeC(clienteDTO.getNome());
        clienteEditar.setCpfC(clienteDTO.getCpf());
        clienteEditar.setSexoC(clienteDTO.getSexo());
        clienteEditar.setTelefoneC(clienteDTO.getTelefone());
        clienteDAO.atualizar(clienteEditar);
    }
    
    public Cliente buscarPorId(Long id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public Cliente buscarPorCPF(String cpf) {
        return clienteDAO.buscarPorCPF(cpf);
    }

    public void excluirCliente(String id) {
        clienteDAO.excluir(id);
    }
}































