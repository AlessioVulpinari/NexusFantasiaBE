package alessiovulpinari.NexusFantasiaBE.payloads.magics;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MagicDTO(@NotEmpty(message = "Il nome della magia non può essere vuoto!") String name,
                       @NotEmpty(message = "La descrizione della magia non può essere vuoto!") String description,
                       @NotNull(message = "Il livello della magia non può essere nullo!") Integer level,
                       @NotEmpty(message = "Il nome della scuola di magia non può essere vuoto!") String magicSchool,
                       @NotEmpty(message = "Il tempo di lancio della magia non può essere vuoto!") String castingTime,
                       @NotEmpty(message = "La portata della magia non può essere vuoto!") String range,
                       @NotEmpty(message = "La durata della magia non può essere vuoto!") String duration,
                       @NotEmpty(message = "La descrizione ad alti livelli della magia non può essere vuoto!") String atHigherLevels,
                       @NotNull(message = "Deve essere specificato se la magia è un rituale o meno !") Boolean isRitual) {
}
