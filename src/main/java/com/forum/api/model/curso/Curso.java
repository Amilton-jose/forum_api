package com.forum.api.model.curso;

import com.forum.api.requests.curso.CursoDTO;
import com.forum.api.requests.topico.DadosAtualizacaoTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    public Curso(CursoDTO curso) {
        this.nome = curso.nome();
        this.categoria = curso.categoria();
    }

    public void atualizarCurso(DadosAtualizacaoTopico dados) {
        if (dados.curso().nome() != null) {
            this.nome = dados.curso().nome();
        }
        if (dados.curso().categoria() != null) {
            this.categoria = dados.curso().categoria();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
