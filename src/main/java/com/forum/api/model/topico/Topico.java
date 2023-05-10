package com.forum.api.model.topico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.forum.api.model.curso.Curso;
import com.forum.api.model.enums.StatusTopico;
import com.forum.api.model.resposta.Resposta;
import com.forum.api.model.usuario.Usuario;
import com.forum.api.requests.topico.DadosAtualizacaoTopico;
import com.forum.api.requests.topico.DadosCadastroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"autor", "curso"})
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensagem;

    @Column(nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @JoinColumn(nullable = false, name = "autor_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario autor;

    @JoinColumn(nullable = false, name = "curso-id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Curso curso;

    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas = new ArrayList<>();


    public Topico(DadosCadastroTopico dados, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = autor;
        this.curso = new Curso(dados.curso());
    }

    public void atualizarInfo(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.titulo();
        }
        if (dados.statusTopico() != null) {
            this.status = dados.statusTopico();
        }
        if (dados.autor() != null) {
            this.autor.atualizarAutor(dados);
        }
        if (dados.curso() != null) {
            this.curso.atualizarCurso(dados);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(id, topico.id) && Objects.equals(titulo, topico.titulo) && Objects.equals(mensagem, topico.mensagem) && Objects.equals(dataCriacao, topico.dataCriacao) && status == topico.status && Objects.equals(autor, topico.autor) && Objects.equals(curso, topico.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, mensagem, dataCriacao, status, autor, curso);
    }
}
