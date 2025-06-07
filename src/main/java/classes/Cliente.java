
package classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbCliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nomeC") 
    private String nomeC;
    
    @Column(name = "cpfC")
    private String cpfC;
    
    @Column(name = "sexoC")
    private String sexoC;
    
    @Column(name = "telefoneC")
    private String telefoneC;

    public Long getId() {
        return id;
    }

    public String getNomeC() {
        return nomeC;
    }

    public String getCpfC() {
        return cpfC;
    }

    public String getSexoC() {
        return sexoC;
    }

    public String getTelefoneC() {
        return telefoneC;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public void setCpfC(String cpfC) {
        this.cpfC = cpfC;
    }

    public void setSexoC(String sexoC) {
        this.sexoC = sexoC;
    }

    public void setTelefoneC(String telefoneC) {
        this.telefoneC = telefoneC;
    }
    
}
