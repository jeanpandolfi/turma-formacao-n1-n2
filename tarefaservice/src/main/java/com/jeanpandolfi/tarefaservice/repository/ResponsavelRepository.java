package com.jeanpandolfi.tarefaservice.repository;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import com.jeanpandolfi.tarefaservice.service.dto.SelectItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, JpaSpecificationExecutor<Responsavel> {

    @Query("SELECT new com.jeanpandolfi.tarefaservice.service.dto.SelectItemDTO( " +
           "r.id, r.nome) " +
           "FROM Responsavel r order by r.id")
    List<SelectItemDTO> findDropDown();
}
