package io.github.robertoaraujo.desafio.infra.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarAnagramaRequestDto(
        @Pattern(regexp = "^(?!\\s*$)[\\p{L}\\s,]+$", message = "A entrada deve conter apenas letras (com ou sem acento), separadas por vírgula")
        @NotBlank(message = "O campo anagrama não pode ser nulo")
        @Size(min = 2, message = "O campo anagrama deve conter pelo menos 2 letras")
        String anagrama) {
}
