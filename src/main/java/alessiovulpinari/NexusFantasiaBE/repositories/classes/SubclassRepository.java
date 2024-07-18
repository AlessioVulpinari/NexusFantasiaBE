package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubclassRepository extends JpaRepository<Subclass, UUID> {
    Optional<Subclass> findByName(String name);
}
