package br.com.base_rest_api.service;

import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequest;
import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequestParams;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoResponse;
import br.com.base_rest_api.domain.entity.Produto;
import br.com.base_rest_api.exception.EntidadeNaoEncontradaException;
import br.com.base_rest_api.mapper.ProdutoMapper;
import br.com.base_rest_api.repository.produto.ProdutoRepository;
import br.com.base_rest_api.repository.produto.ProdutoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    public Page<ProdutoPesquisaResponse> pesquisarProdutos(ProdutoRequestParams produtoRequestParams, Pageable pageable) {
        ProdutoSpecification produtoSpecification = new ProdutoSpecification(produtoRequestParams);
        Page<Produto> produtoPage = this.produtoRepository.findAll(produtoSpecification, pageable);
        return produtoPage.map(this.produtoMapper::converterParaProdutoPesquisaResponse);
    }

    public ProdutoResponse obterProduto(Long idProduto) {
        Produto produto = obterProdutoPorId(idProduto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    @Transactional
    public ProdutoResponse criarProduto(ProdutoRequest produtoRequest) {
        Produto produto = this.produtoMapper.converterParaProduto(produtoRequest);
        produto = this.produtoRepository.save(produto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    @Transactional
    public ProdutoResponse atualizarProduto(Long idProduto, ProdutoRequest produtoRequest) {
        Produto produto = obterProdutoPorId(idProduto);
        this.produtoMapper.converterParaProduto(produto, produtoRequest);
        produto = this.produtoRepository.save(produto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    public Produto obterProdutoPorId(Long idProduto) {
        return this.produtoRepository.findById(idProduto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado", idProduto));
    }

}