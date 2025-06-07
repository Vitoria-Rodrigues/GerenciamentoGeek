
package classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tbCategoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nomeCateg")
    private String nomeCateg;

    public Long getId() {
        return id;
    }

    public String getNomeCateg() {
        return nomeCateg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }
    
}
