package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record LanguageNameDTO(@NotEmpty String languageName) {
}
