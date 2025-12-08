
import DTO.ClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ClienteTest {
    @Test
    
    void criarClienteNovo(){
        ClienteDTO clienteDTO = new ClienteDTO(
                "Amanda Ornelas", 
                "14203578620", 
                "Feminino",
                "11920456841");
        
        Assertions.assertEquals("Amanda Ornelas", clienteDTO.getNome());
        Assertions.assertEquals("14203578620", clienteDTO.getCpf());
        Assertions.assertEquals("Feminino", clienteDTO.getSexo());
        Assertions.assertEquals("11920456841", clienteDTO.getTelefone());

    }
    
    @Test
    void criarClienteErro(){
        ClienteDTO clienteDTO = new ClienteDTO(
                "Felipe Guimarães", 
                "25714836510",
                "Masculino",
                "11941785420");
        
        System.out.println("Erro esperado por ter valores diferentes sendo passados no assertions");
        
        Assertions.assertEquals("Felipe Guimarães", clienteDTO.getNome());
        Assertions.assertEquals("25714836510", clienteDTO.getCpf());
        Assertions.assertEquals("Feminino", clienteDTO.getSexo());
        Assertions.assertEquals("11941785420", clienteDTO.getTelefone());
    }
}
