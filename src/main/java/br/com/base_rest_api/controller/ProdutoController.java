package br.com.base_rest_api.controller;

import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequest;
import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequestParams;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoResponse;
import br.com.base_rest_api.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoPesquisaResponse>> pesquisarProdutos(@Nullable ProdutoRequestParams produtoRequestParams, @Nullable Pageable pageable) {
        Page<ProdutoPesquisaResponse> produtoPesquisaResponsePage = this.produtoService.pesquisarProdutos(produtoRequestParams, pageable);
        return ResponseEntity.ok(produtoPesquisaResponsePage);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> obterProduto(@PathVariable Long idProduto) {
        ProdutoResponse produtoResponse = this.produtoService.obterProduto(idProduto);
        return ResponseEntity.ok(produtoResponse);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = this.produtoService.criarProduto(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long idProduto, @RequestBody @Valid ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = this.produtoService.atualizarProduto(idProduto, produtoRequest);
        return ResponseEntity.ok(produtoResponse);
    }

}