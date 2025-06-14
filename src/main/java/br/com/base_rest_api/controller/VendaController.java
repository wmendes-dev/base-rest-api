package br.com.base_rest_api.controller;

import br.com.base_rest_api.domain.dto.request.venda.VendaRequest;
import br.com.base_rest_api.domain.dto.request.venda.VendaRequestParams;
import br.com.base_rest_api.domain.dto.response.venda.VendaPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.venda.VendaResponse;
import br.com.base_rest_api.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @GetMapping
    public ResponseEntity<Page<VendaPesquisaResponse>> pesquisarVendas(@Nullable VendaRequestParams vendaRequestParams, @Nullable Pageable pageable) {
        Page<VendaPesquisaResponse> vendaPesquisaResponsePage = this.vendaService.pesquisarVendas(vendaRequestParams, pageable);
        return ResponseEntity.ok(vendaPesquisaResponsePage);
    }

    @GetMapping("/{idVenda}")
    public ResponseEntity<VendaResponse> obterVenda(@PathVariable Long idVenda) {
        VendaResponse vendaResponse = this.vendaService.obterVenda(idVenda);
        return ResponseEntity.ok(vendaResponse);
    }

    @PostMapping
    public ResponseEntity<VendaResponse> criarVenda(@RequestBody @Valid VendaRequest vendaRequest) {
        VendaResponse vendaResponse = this.vendaService.criarVenda(vendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaResponse);
    }

    @PutMapping("/{idVenda}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable Long idVenda, @RequestBody @Valid VendaRequest vendaRequest) {
        VendaResponse vendaResponse = this.vendaService.atualizarVenda(idVenda, vendaRequest);
        return ResponseEntity.ok(vendaResponse);
    }

}