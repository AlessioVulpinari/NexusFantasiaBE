package alessiovulpinari.NexusFantasiaBE.payloads.equipments;

import jakarta.validation.constraints.NotEmpty;

public record EquipmentNameDTO(@NotEmpty(message = "Il nome dell'oggetto non può essere vuoto!") String name) {
}
