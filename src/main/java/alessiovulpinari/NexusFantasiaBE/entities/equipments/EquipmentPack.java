package alessiovulpinari.NexusFantasiaBE.entities.equipments;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EquipmentPack extends Equipment {

    @ManyToMany
    @JoinTable(
            name = "pacchetti_equipaggiamenti",
            joinColumns = @JoinColumn(name = "id_pacchetto"),
            inverseJoinColumns = @JoinColumn(name = "id_equipaggiamento"))
    private List<Equipment> equipmentList;

    public EquipmentPack(String name, String description, double weight, double cost) {
        super(name, description, weight, cost);
        this.equipmentList = new ArrayList<>();
    }
}
