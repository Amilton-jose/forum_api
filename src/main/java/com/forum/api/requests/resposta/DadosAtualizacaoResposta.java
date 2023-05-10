package com.forum.api.requests.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        @NotBlank
        String mensagem,
        @NotNull
        boolean solucao
) {
}
