package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record BackgroundFeatureNameDTO(@NotEmpty String backgroundFeatureName) {
}
