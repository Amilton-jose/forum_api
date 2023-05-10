package com.forum.api.model.resposta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.forum.api.model.topico.Topico;
import com.forum.api.model.usuario.Usuario;
import com.forum.api.requests.resposta.DadosAtualizacaoResposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"autor", "topico"})
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime dataCriacao = LocalDateTime.now(ZoneOffset.of("-03:00"));

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean solucao = false;

    public Resposta(String mensagem, Topico topico, Usuario autor) {
        this.mensagem = mensagem;
        this.topico = topico;
        this.autor = autor;
    }

    public void atualizarInfo(DadosAtualizacaoResposta dados) {
        this.mensagem = dados.mensagem();
    }
}
