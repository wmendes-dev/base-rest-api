package br.com.base_rest_api.repository.venda;

import br.com.base_rest_api.domain.dto.request.venda.VendaRequestParams;
import br.com.base_rest_api.domain.dto.response.venda.VendaPesquisaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendaCustomRepository {

    Page<VendaPesquisaResponse> findAll(VendaRequestParams vendaRequestParams, Pageable pageable);

}