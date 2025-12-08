
import DTO.ProdutoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ProdutoTest {
    @Test
    
    void criarProdutoFunkoPop(){
        ProdutoDTO produtoDTO = new ProdutoDTO(
                "Funko Pop Homem de Ferro", 
                "Boneco colecionável Funko Pop do Homem de Ferro.",
                119.90, 
                18, 
                1007,
                Long.parseLong("7"));
        
        Assertions.assertEquals("Funko Pop Homem de Ferro", produtoDTO.getNome());
        Assertions.assertEquals("Boneco colecionável Funko Pop do Homem de Ferro.", produtoDTO.getDesc());
        Assertions.assertEquals(119.90, produtoDTO.getPreco());
        Assertions.assertEquals(18, produtoDTO.getQtdEstoque());
        Assertions.assertEquals(1007, produtoDTO.getCodigo());
        Assertions.assertEquals(Long.parseLong("7"), produtoDTO.getCategoria());

    }
    
    @Test
    void criarProdutoControleErro(){
        ProdutoDTO produtoDTO = new ProdutoDTO(
                "Controle Sem Fio Xbox One", 
                "Controle original sem fio compatível com Xbox One e PC.", 
                349.99,
                30, 
                1003, 
                Long.parseLong("3"));
        
        System.out.println("Erro esperado por ter valores diferentes sendo passados no assertions");
        
        Assertions.assertEquals("Controle Sem Fio Xbox One", produtoDTO.getNome());
        Assertions.assertEquals("Controle original sem fio compatível com Xbox One e PC.", produtoDTO.getDesc());
        Assertions.assertEquals(450.50, produtoDTO.getPreco());
        Assertions.assertEquals(35, produtoDTO.getQtdEstoque());
        Assertions.assertEquals(1003, produtoDTO.getCodigo());
        Assertions.assertEquals(Long.parseLong("7"), produtoDTO.getCategoria());
    }
}
