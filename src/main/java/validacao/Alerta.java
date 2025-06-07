
package validacao;

import telas.telaErro;
import telas.telaSucesso;

public class Alerta {
    public static void Erro(String titulo, String msg){
        new telaErro(null, true, titulo, msg).setVisible(true);
    }
    
    public static void Sucesso(String titulo, String msg){
        new telaSucesso(null, true, titulo, msg).setVisible(true);
    }
}
