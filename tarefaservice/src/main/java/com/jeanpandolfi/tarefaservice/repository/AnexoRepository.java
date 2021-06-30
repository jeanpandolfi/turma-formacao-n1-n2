package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
}
