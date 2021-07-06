package com.jeanpandolfi.tarefaservice.service.filtro;

import com.jeanpandolfi.tarefaservice.domain.Responsavel_;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsavelFiltro implements Serializable {
    private String nome;
    private String email;
    private String status;

    public Specification filter() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(getPredicates(root, criteriaBuilder).toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates(Root root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(nome)) {
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(Responsavel_.nome)), "%" + nome.toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(email)) {
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(Responsavel_.email)), "%" + email.toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(status)) {
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(Responsavel_.status)), "%" + status.toLowerCase() + "%");
            predicates.add(predicate);
        }

        return predicates;
    }
}
