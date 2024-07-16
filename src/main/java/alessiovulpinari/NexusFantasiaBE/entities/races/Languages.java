package alessiovulpinari.NexusFantasiaBE.entities.races;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "linguaggi")
public class Languages {

    @Id
    @GeneratedValue
    @Column(name = "id_lingua", nullable = false)
    private UUID languageId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    public Languages(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
