package com.forum.api.requests.resposta;

import com.forum.api.model.resposta.Resposta;
import com.forum.api.model.topico.Topico;

public record DadosDetalhamentoRespota(String autor,Topico topico){
    public DadosDetalhamentoRespota(Resposta resposta){
        this(resposta.getAutor().getNome(),resposta.getTopico());
    }


}
