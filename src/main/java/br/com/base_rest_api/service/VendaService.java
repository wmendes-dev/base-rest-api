package br.com.base_rest_api.service;

import br.com.base_rest_api.domain.dto.request.venda.VendaRequest;
import br.com.base_rest_api.domain.dto.request.venda.VendaRequestParams;
import br.com.base_rest_api.domain.dto.response.venda.VendaPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.venda.VendaResponse;
import br.com.base_rest_api.domain.entity.Venda;
import br.com.base_rest_api.exception.EntidadeNaoEncontradaException;
import br.com.base_rest_api.mapper.VendaMapper;
import br.com.base_rest_api.repository.venda.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;

    private final VendaMapper vendaMapper;

    public Page<VendaPesquisaResponse> pesquisarVendas(VendaRequestParams vendaRequestParams, Pageable pageable) {
        return this.vendaRepository.findAll(vendaRequestParams, pageable);
    }

    public VendaResponse obterVenda(Long idVenda) {
        Venda venda = obterVendaPorId(idVenda);
        return this.vendaMapper.converterParaVendaResponse(venda);
    }

    @Transactional
    public VendaResponse criarVenda(VendaRequest vendaRequest) {
        Venda venda = this.vendaMapper.converterParaVenda(vendaRequest);
        venda = this.vendaRepository.save(venda);
        return this.vendaMapper.converterParaVendaResponse(venda);
    }

    @Transactional
    public VendaResponse atualizarVenda(Long idVenda, VendaRequest vendaRequest) {
        Venda venda = obterVendaPorId(idVenda);
        this.vendaMapper.converterParaVenda(venda, vendaRequest);
        venda = this.vendaRepository.save(venda);
        return this.vendaMapper.converterParaVendaResponse(venda);
    }

    public Venda obterVendaPorId(Long idVenda) {
        return this.vendaRepository.findById(idVenda)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrada", idVenda));
    }

}