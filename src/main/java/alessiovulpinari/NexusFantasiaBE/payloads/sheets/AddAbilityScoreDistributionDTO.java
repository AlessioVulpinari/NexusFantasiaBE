package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record AddAbilityScoreDistributionDTO(@NotEmpty(message = "La metodologia per l'assegnazione delle statistiche non può essere vuota!") String abilityScoreDistribution) {
}
