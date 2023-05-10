package com.forum.api.requests.resposta;

import com.forum.api.model.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(Long id,String mensagem, LocalDateTime dataCriacao, boolean solucao) {
    public DadosListagemResposta(Resposta r){
        this(r.getId(), r.getMensagem(),r.getDataCriacao(),r.getSolucao());
    }
}
