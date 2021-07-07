package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT new com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument( " +
            "t.id, " +
            "t.titulo, " +
            "t.dataInicioPrevista, " +
            "t.dataTerminoPrevista, " +
            "t.dataInicio, " +
            "t.dataConclusao, " +
            "t.tipo, " +
            "t.status, " +
            "t.tempoPrevisto, " +
            "t.tempoGasto, " +
            "t.responsavel.nome, " +
            "t.responsavel.id) " +
            "FROM Tarefa t where t.id = :idTarefa")
    TarefaDocument getDocument(@Param("idTarefa") Long idTarefa);

    @Query("SELECT new com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument( " +
            "t.id, " +
            "t.titulo, " +
            "t.dataInicioPrevista, " +
            "t.dataTerminoPrevista, " +
            "t.dataInicio, " +
            "t.dataConclusao, " +
            "t.tipo, " +
            "t.status, " +
            "t.tempoPrevisto, " +
            "t.tempoGasto, " +
            "t.responsavel.nome, " +
            "t.responsavel.id) FROM Tarefa t order by t.id")
    List<TarefaDocument> getDocuments();
}
