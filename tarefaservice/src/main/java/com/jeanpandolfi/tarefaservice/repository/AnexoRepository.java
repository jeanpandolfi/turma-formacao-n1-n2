package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    @Query("SELECT a.chaveMinio FROM Anexo a WHERE a.id = :id")
    String findChaveMinioById(@Param("id") Long id);

    @Query("SELECT new com.jeanpandolfi.tarefaservice.service.dto.AnexoListDTO(a.id, a.titulo, a.tamanho) FROM Anexo a WHERE a.tarefa.id = :idTarefa")
    List<AnexoListDTO> findAnexosByTarefa(@Param("idTarefa") Long idTarefa);
}
