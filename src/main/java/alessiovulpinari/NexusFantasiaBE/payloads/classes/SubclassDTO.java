package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;

public record SubclassDTO(@NotEmpty(message = "II nome della sotto classe non può essere vuoto!")  String name,
                          @NotEmpty(message = "La descrizione della sotto classe non può essere vuoto!")  String description,
                          @NotEmpty(message = "II nome della classe non può essere vuoto!") String className) {
}
