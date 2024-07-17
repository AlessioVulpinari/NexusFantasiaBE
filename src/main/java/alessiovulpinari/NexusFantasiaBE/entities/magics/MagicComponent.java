package alessiovulpinari.NexusFantasiaBE.entities.magics;

import alessiovulpinari.NexusFantasiaBE.enums.ComponentSymbol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "componenti")
public class MagicComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_componente", nullable = false)
    private UUID magicComponentId;

    @Column(name = "nome" , nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @Column(name = "simbolo", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ComponentSymbol componentSymbol;

    public MagicComponent(String name, String description, ComponentSymbol componentSymbol) {
        this.name = name;
        this.description = description;
        this.componentSymbol = componentSymbol;
    }
}
