package alessiovulpinari.NexusFantasiaBE.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ruolo")
@NoArgsConstructor
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruolo", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}
