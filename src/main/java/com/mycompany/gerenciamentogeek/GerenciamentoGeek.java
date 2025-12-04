
package com.mycompany.gerenciamentogeek;

import DTO.ClienteDTO;
import classes.Cliente;
import controllers.ClienteController;
import java.util.List;

public class GerenciamentoGeek {

    public static void main(String[] args) {
        ClienteController cc = new ClienteController();
        ClienteDTO clienteDTO = new ClienteDTO("Tupynamba Lucas", "14252125199",
                "Feminino", "11955443322");
        cc.cadastrarCliente(clienteDTO);
        
        Cliente cliente = cc.buscarClientePorCPF("14252125199");
        
        List<Cliente> listarCliente = cc.listarClientes();
        
        cc.excluirCliente("2");
    }
}
