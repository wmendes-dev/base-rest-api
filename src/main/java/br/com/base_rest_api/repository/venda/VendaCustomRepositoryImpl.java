package br.com.base_rest_api.repository.venda;

import br.com.base_rest_api.domain.dto.request.venda.VendaRequestParams;
import br.com.base_rest_api.domain.dto.response.venda.VendaPesquisaResponse;
import br.com.base_rest_api.domain.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaCustomRepositoryImpl implements VendaCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<VendaPesquisaResponse> findAll(VendaRequestParams vendaRequestParams, Pageable pageable) {
        VendaQuery vendaQuery = new VendaQuery();

        Long count = vendaQuery.count(vendaRequestParams);
        if (count.equals(0L)) return Page.empty(pageable);

        List<VendaPesquisaResponse> list = vendaQuery.list(vendaRequestParams, pageable);

        return new PageImpl<>(list, pageable, count);
    }

    class VendaQuery {

        private final CriteriaBuilder criteriaBuilder;
        private Root<Venda> fromVenda;
        private Join<Object, Object> joinCliente;

        public VendaQuery() {
            this.criteriaBuilder = entityManager.getCriteriaBuilder();
        }

        public Long count(VendaRequestParams vendaRequestParams) {
            CriteriaQuery<Long> queryCount = this.criteriaBuilder.createQuery(Long.class);

            prepararClausulas(queryCount);

            queryCount.select(this.criteriaBuilder.count(fromVenda.get("idVenda")));

            aplicarFiltros(queryCount, vendaRequestParams);

            return entityManager.createQuery(queryCount).getSingleResult();
        }

        public List<VendaPesquisaResponse> list(VendaRequestParams vendaRequestParams, Pageable pageable) {
            CriteriaQuery<VendaPesquisaResponse> querySelect = this.criteriaBuilder.createQuery(VendaPesquisaResponse.class);

            prepararClausulas(querySelect);

            querySelect.select(this.criteriaBuilder.construct(
                    VendaPesquisaResponse.class,
                    this.fromVenda.get("idVenda").alias("idVenda"),
                    this.fromVenda.get("dataEmissao").alias("dataEmissao"),
                    this.fromVenda.get("valorTotal").alias("valorTotal"),
                    this.joinCliente.get("nomeRazaoSocial").alias("cliente")
            ));

            aplicarFiltros(querySelect, vendaRequestParams);

            TypedQuery<VendaPesquisaResponse> query = entityManager.createQuery(querySelect);
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            return query.getResultList();
        }

        private void prepararClausulas(CriteriaQuery<?> criteriaQuery) {
            this.fromVenda = criteriaQuery.from(Venda.class);
            this.joinCliente = this.fromVenda.join("cliente");
        }

        private void aplicarFiltros(CriteriaQuery<?> criteriaQuery, VendaRequestParams vendaRequestParams) {
            List<Predicate> predicates = new ArrayList<>();

            String cliente = vendaRequestParams.cliente();
            if (!ObjectUtils.isEmpty(cliente)) {
                predicates.add(this.criteriaBuilder.like(this.joinCliente.get("nomeRazaoSocial"), "%" + cliente + "%"));
            }

            LocalDate dataInicio = vendaRequestParams.dataInicio();
            if (!ObjectUtils.isEmpty(dataInicio)) {
                predicates.add(this.criteriaBuilder.greaterThanOrEqualTo(this.fromVenda.get("dataEmissao"), dataInicio));
            }

            LocalDate dataFim = vendaRequestParams.dataFim();
            if (!ObjectUtils.isEmpty(dataFim)) {
                predicates.add(this.criteriaBuilder.lessThanOrEqualTo(this.fromVenda.get("dataEmissao"), dataFim));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(predicates.toArray(predicateArray));
        }

    }

}