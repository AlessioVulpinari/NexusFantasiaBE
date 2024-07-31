package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CharacterSheetDTO(@NotEmpty(message = "Il nome del personaggio della scheda non può essere nullo!") String name,
                                @NotEmpty(message = "La metodologia per l'assegnazione delle statistiche non può essere vuota!") String abilityScoreDistribution,
                                @NotNull(message = "L'età del personaggio non può essere nulla!") Integer age,
                                @NotEmpty(message = "L'altezza del personaggio non può essere vuota!") String height,
                                @NotEmpty(message = "Il peso del personaggio non può essere vuoto!") String weight,
                                @NotEmpty(message = "Il colore degli occhi del personaggio non può essere vuoto!") String eyeColor,
                                @NotEmpty(message = "Il colore dei capelli del personaggio non può essere vuoto!") String hairColor,
                                @NotEmpty(message = "La carnagione del personaggio non può essere vuota!") String complexion,
                                @NotEmpty(message = "L'allineamento del personaggio non può essere vuoto!") String alignment,
                                @NotEmpty(message = "Il carattere del personaggio non può essere vuoto!") String personalTraits,
                                @NotEmpty(message = "Gli ideali del personaggio non possono essere vuoti") String ideals,
                                @NotEmpty(message = "I legami del personaggio non possono essere vuoti") String bonds,
                                @NotEmpty(message = "I difetti del personaggio non possono essere vuoti") String flaw,
                                @NotEmpty(message = "Il background non può essere vuoto!") String backgroundName,
                                Set<Languages> languagesSet,
                                @NotEmpty(message = "Il nome della razza non può essere vuoto!") String raceName,
                                @NotEmpty(message = "Il nome della sotto razza non può essere vuoto!") String subRaceName,
                                @NotEmpty(message = "Il nome della classe non può essere vuoto!") String className,
                                String subclassName,
                                @NotNull(message = "La forza non può essere nulla!") Integer strength,
                                @NotNull(message = "La destrezza non può essere nulla!") Integer dexterity,
                                @NotNull(message = "La costituzione non può essere nulla!") Integer constitution,
                                @NotNull(message = "L'intelligenza non può essere nulla!") Integer intelligence,
                                @NotNull(message = "La saggezza non può essere nulla!") Integer wisdom,
                                @NotNull(message = "Il carisma non può essere nullo!") Integer charisma) {
}
