package alessiovulpinari.NexusFantasiaBE.repositories.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
}
