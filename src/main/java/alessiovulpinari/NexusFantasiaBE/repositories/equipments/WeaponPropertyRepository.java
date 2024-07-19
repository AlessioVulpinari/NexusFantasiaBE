package alessiovulpinari.NexusFantasiaBE.repositories.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.WeaponProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeaponPropertyRepository extends JpaRepository<WeaponProperty, UUID> {
}
