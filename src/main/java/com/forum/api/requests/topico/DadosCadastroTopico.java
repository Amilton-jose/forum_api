package com.forum.api.requests.topico;

import com.forum.api.requests.curso.CursoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long autorId,
        @NotNull
        @Valid CursoDTO curso) {
}
