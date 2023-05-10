package com.forum.api.requests.topico;

import com.forum.api.model.enums.StatusTopico;
import com.forum.api.model.resposta.Resposta;
import com.forum.api.model.topico.Topico;

import java.time.LocalDate;
import java.util.List;


public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDate dataCriacao, StatusTopico status, String autor, String
        curso, List<Resposta> respostas) {
    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(),topico.getStatus(),topico.getAutor().getNome(),topico.getCurso().getNome(),topico.getRespostas());
    }

}
