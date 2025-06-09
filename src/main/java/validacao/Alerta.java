
package validacao;

import telas.telaCliente;
import telas.telaErro;
import telas.telaSucesso;

public class Alerta {
    public static void Erro(String titulo, String msg){
        new telaErro(null, true, titulo, msg).setVisible(true);
    }
    
    public static void Sucesso(String titulo, String msg){
        new telaSucesso(null, true, titulo, msg).setVisible(true);
    }
    
    public static void Cliente(String titulo, String cpf, String tel){
        new telaCliente(null, true, titulo, cpf, tel).setVisible(true);
    }
}
