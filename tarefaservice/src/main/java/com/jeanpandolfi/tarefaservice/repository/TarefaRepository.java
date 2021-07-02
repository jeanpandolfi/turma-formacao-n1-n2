package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.domain.elasticsearch.TarefaDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT new com.jeanpandolfi.tarefaservice.domain.elasticsearch.TarefaDocument( " +
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
            "t.responsavel.nome) " +
            "FROM Tarefa t where t.id = :idTarefa")
    TarefaDocument getDocument(@Param("idTarefa") Long idTarefa);
}
