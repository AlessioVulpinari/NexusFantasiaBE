package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record AddClassDTO(@NotEmpty(message = "Il nome della classe non può essere vuoto!") String className,
                          String subclassName) {
}
