package br.com.base_rest_api.repository.produto;

import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequestParams;
import br.com.base_rest_api.domain.entity.Produto;
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
public class ProdutoSpecification implements Specification<Produto> {

    private final ProdutoRequestParams produtoRequestParams;

    @Override
    public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(this.produtoRequestParams.nome())) {
            predicates.add(criteriaBuilder.like(root.get("nomeRazaoSocial"), "%" + this.produtoRequestParams.nome() + "%"));
        }

        Predicate[] predicateArray = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(predicateArray));
    }

}