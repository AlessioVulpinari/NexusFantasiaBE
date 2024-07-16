package alessiovulpinari.NexusFantasiaBE.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tratti_armi")
public class WeaponProperty {

    @Id
    @GeneratedValue
    @Column(name = "id_tratto_arma", nullable = false)
    private UUID weaponPropertyId;

    @Column(name = "nome")
    private String name;

    @Column(name = "description")
    private String description;

    public WeaponProperty(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
