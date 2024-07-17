package alessiovulpinari.NexusFantasiaBE.entities.equipments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Container extends Equipment {

    @Column(name = "capacità")
    private String capacity;

    public Container(String name, String description, String weight, String cost, String capacity) {
        super(name, description, weight, cost);
        this.capacity = capacity;
    }
}
