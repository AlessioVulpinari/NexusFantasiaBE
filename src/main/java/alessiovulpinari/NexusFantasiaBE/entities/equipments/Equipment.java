package alessiovulpinari.NexusFantasiaBE.entities.equipments;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "equipaggiamento")
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_equipaggiamento", nullable = false)
    private UUID equipmentId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @Column(name = "peso", nullable = false)
    private String weight;

    @Column(name = "costo", nullable = false)
    private String cost;

    public Equipment(String name, String description, String weight, String cost) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.cost = cost;
    }
}
