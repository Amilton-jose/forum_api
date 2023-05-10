package com.forum.api.requests.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem,

        Long topicoId,

        Long autorId,
        boolean solucao) {

}
