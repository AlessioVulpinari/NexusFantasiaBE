package alessiovulpinari.NexusFantasiaBE.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ruolo")
@NoArgsConstructor
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ruolo", nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}
