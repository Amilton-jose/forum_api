package com.forum.api.requests.topico;

import com.forum.api.model.enums.StatusTopico;
import com.forum.api.model.topico.Topico;

import java.time.LocalDate;

public record DadosListagemTopico(String titulo, String mensagem, LocalDate dataCriacao, StatusTopico statusTopico,
                                  String nome) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(),topico.getCurso().getNome());
    }

}
