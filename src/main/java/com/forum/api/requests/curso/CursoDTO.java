package com.forum.api.requests.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        @NotBlank
        String nome,
        @NotBlank
        String categoria) {
}
