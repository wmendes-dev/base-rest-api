package br.com.base_rest_api.service;

import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequest;
import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequestParams;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoResponse;
import br.com.base_rest_api.domain.entity.Produto;
import br.com.base_rest_api.domain.enums.SituacaoEnum;
import br.com.base_rest_api.exception.EntidadeNaoEncontradaException;
import br.com.base_rest_api.mapper.ProdutoMapper;
import br.com.base_rest_api.repository.produto.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void devePesquisarProdutosComSucesso() {
        ProdutoRequestParams produtoRequestParams = new ProdutoRequestParams("Produto 1");
        Produto produto = new Produto(1L, "Produto 1", BigDecimal.ONE, SituacaoEnum.ATIVO);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Produto> produtoPage = new PageImpl<>(List.of(produto));
        ProdutoPesquisaResponse produtoPesquisaResponse = new ProdutoPesquisaResponse(1L, "Produto 1", BigDecimal.ONE);

        Mockito.when(this.produtoRepository.findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.eq(pageable)))
                .thenReturn(produtoPage);
        Mockito.when(this.produtoMapper.converterParaProdutoPesquisaResponse(produto))
                .thenReturn(produtoPesquisaResponse);

        Page<ProdutoPesquisaResponse> resultado = this.produtoService.pesquisarProdutos(produtoRequestParams, pageable);

        Assertions.assertThat(resultado).isNotEmpty();
        Assertions.assertThat(resultado.getContent()).hasSize(1);
        Assertions.assertThat(resultado.getContent().getFirst().nome()).isEqualTo("Produto 1");

        Mockito.verify(this.produtoRepository).findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.eq(pageable));
        Mockito.verify(this.produtoMapper).converterParaProdutoPesquisaResponse(produto);
        Mockito.verifyNoMoreInteractions(this.produtoRepository, this.produtoMapper);
    }

    @Test
    void deveObterProdutoComSucesso() {
        Produto produto = new Produto(1L, "Produto 1", BigDecimal.ONE, SituacaoEnum.ATIVO);
        ProdutoResponse produtoResponse = new ProdutoResponse(1L, "Produto 1", BigDecimal.ONE);

        Mockito.when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(this.produtoMapper.converterParaProdutoResponse(produto)).thenReturn(produtoResponse);

        ProdutoResponse resultado = this.produtoService.obterProduto(1L);

        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.idProduto()).isEqualTo(1L);

        Mockito.verify(this.produtoRepository).findById(1L);
        Mockito.verify(this.produtoMapper).converterParaProdutoResponse(produto);
        Mockito.verifyNoMoreInteractions(this.produtoRepository, this.produtoMapper);
    }

    @Test
    void deveCriarProdutoComSucesso() {
        ProdutoRequest produtoRequest = new ProdutoRequest("Produto 1", BigDecimal.ONE, SituacaoEnum.ATIVO);
        Produto produto = new Produto(1L, "Produto 1", BigDecimal.ONE, SituacaoEnum.ATIVO);
        ProdutoResponse produtoCriadoResponse = new ProdutoResponse(1L, "Produto 1", BigDecimal.ONE);

        Mockito.when(this.produtoMapper.converterParaProduto(produtoRequest)).thenReturn(produto);
        Mockito.when(this.produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(this.produtoMapper.converterParaProdutoResponse(produto)).thenReturn(produtoCriadoResponse);

        ProdutoResponse resultado = this.produtoService.criarProduto(produtoRequest);

        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.idProduto()).isEqualTo(1L);

        Mockito.verify(this.produtoMapper).converterParaProduto(produtoRequest);
        Mockito.verify(this.produtoRepository).save(produto);
        Mockito.verify(this.produtoMapper).converterParaProdutoResponse(produto);
        Mockito.verifyNoMoreInteractions(this.produtoRepository);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Produto 1", BigDecimal.ONE, SituacaoEnum.ATIVO);
        ProdutoRequest produtoAtualizadoRequest = new ProdutoRequest("Produto 1 Atualizado", BigDecimal.TWO, SituacaoEnum.ATIVO);
        ProdutoResponse produtoAtualizadoResponse = new ProdutoResponse(1L, "Produto 1 Atualizado", BigDecimal.TWO);

        Mockito.when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.doNothing().when(this.produtoMapper).converterParaProduto(produto, produtoAtualizadoRequest);
        Mockito.when(this.produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(this.produtoMapper.converterParaProdutoResponse(produto)).thenReturn(produtoAtualizadoResponse);

        ProdutoResponse resultado = this.produtoService.atualizarProduto(1L, produtoAtualizadoRequest);

        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.idProduto()).isEqualTo(1L);
        Assertions.assertThat(resultado.nome()).isEqualTo("Produto 1 Atualizado");
        Assertions.assertThat(resultado.valor()).isEqualTo(BigDecimal.TWO);

        Mockito.verify(this.produtoRepository).findById(1L);
        Mockito.verify(this.produtoMapper).converterParaProduto(produto, produtoAtualizadoRequest);
        Mockito.verify(this.produtoRepository).save(produto);
        Mockito.verify(this.produtoMapper).converterParaProdutoResponse(produto);
        Mockito.verifyNoMoreInteractions(this.produtoRepository);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        Mockito.when(this.produtoRepository.findById(999L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> this.produtoService.obterProduto(999L))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Produto não encontrado");

        Mockito.verify(this.produtoRepository).findById(999L);
        Mockito.verifyNoMoreInteractions(this.produtoRepository);
        Mockito.verifyNoInteractions(this.produtoMapper);
    }

}
