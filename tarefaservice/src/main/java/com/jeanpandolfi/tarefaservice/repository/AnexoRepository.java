package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    @Query("SELECT a.chaveMinio FROM Anexo a WHERE a.id = :id")
    String findChaveMinioById(@Param("id") Long id);
}
