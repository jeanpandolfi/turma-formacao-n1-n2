package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
