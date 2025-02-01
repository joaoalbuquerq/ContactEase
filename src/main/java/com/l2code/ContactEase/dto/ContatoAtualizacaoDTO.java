package com.l2code.ContactEase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContatoAtualizacaoDTO(
        @Size(max = 100) String nome,

        @Email @Size(max = 255) String email,

        @Pattern(regexp = "^\\(?(\\d{2})\\)?\\s?9?\\d{4}-?\\d{4}$", message = "Número de celular inválido")

        @Size(min = 11, max = 11) String celular,

        @Pattern(regexp = "^\\(?(\\d{2})\\)?\\s?\\d{4}-?\\d{4}$", message = "Número de telefone inválido")

        @Size(min = 10, max = 10) String telefone
) {
}
