package com.forum.api.repository.topico;

import com.forum.api.model.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNomeContaining(Pageable pageable, String nome);
    @Query("SELECT t FROM Topico t WHERE YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByAno(Pageable pageable, @Param("ano") Integer ano);
    Page<Topico> findAllByDataCriacaoBetween(Pageable pageable, LocalDate startDate, LocalDate endDate);

    boolean existsByMensagem(String mensagem);
    boolean existsByTitulo(String titulo);

}
