package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RaceDTO(@NotEmpty(message = "Il nome della razza non può essere vuoto!") String name,
                      @NotEmpty(message = "La descrizione dell'età della razza non può essere vuota!") String ageDescription,
                      @NotEmpty(message = "La descrizione dell'allineamento della razza non può essere vuota!") String alignmentDescription,
                      @NotEmpty(message = "La descrizione delle dimensioni della razza non può essere vuota!") String sizeDescription,
                      @NotNull(message = "La velocità della razza non può essere nulla!") Integer speed) {
}
