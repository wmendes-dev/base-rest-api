package br.com.base_rest_api.controller;

import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequest;
import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequestParams;
import br.com.base_rest_api.domain.dto.response.cliente.ClientePesquisaResponse;
import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;
import br.com.base_rest_api.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ClientePesquisaResponse>> pesquisarClientes(@Nullable ClienteRequestParams clienteRequestParams, @Nullable Pageable pageable) {
        Page<ClientePesquisaResponse> clientePesquisaResponsePage = this.clienteService.pesquisarClientes(clienteRequestParams, pageable);
        return ResponseEntity.ok(clientePesquisaResponsePage);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> obterCliente(@PathVariable Long idCliente) {
        ClienteResponse clienteResponse = this.clienteService.obterCliente(idCliente);
        return ResponseEntity.ok(clienteResponse);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid ClienteRequest clienteRequest) {
        ClienteResponse clienteResponse = this.clienteService.criarCliente(clienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteRequest clienteRequest) {
        ClienteResponse clienteResponse = this.clienteService.atualizarCliente(idCliente, clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long idCliente) {
        this.clienteService.removerCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remover-lista")
    public ResponseEntity<Void> removerClientes(@RequestBody List<Long> idClienteList) {
        this.clienteService.removerClientes(idClienteList);
        return ResponseEntity.noContent().build();
    }

}