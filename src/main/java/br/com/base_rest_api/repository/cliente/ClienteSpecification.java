package br.com.base_rest_api.repository.cliente;

import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequestParams;
import br.com.base_rest_api.domain.entity.Cliente;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClienteSpecification implements Specification<Cliente> {

    private final ClienteRequestParams clienteRequestParams;

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(this.clienteRequestParams.nomeRazaoSocial())) {
            predicates.add(criteriaBuilder.like(root.get("nomeRazaoSocial"), "%" + this.clienteRequestParams.nomeRazaoSocial() + "%"));
        }

        Predicate[] predicateArray = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(predicateArray));
    }

}