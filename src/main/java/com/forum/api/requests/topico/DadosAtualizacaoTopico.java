package com.forum.api.requests.topico;

import com.forum.api.model.enums.StatusTopico;
import com.forum.api.requests.curso.CursoDTO;
import com.forum.api.requests.usuario.UsuarioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        StatusTopico statusTopico,
        @NotNull
        @Valid UsuarioDTO autor,
        @NotNull
        @Valid CursoDTO curso) {
}
