package br.com.base_rest_api.service;

import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequest;
import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequestParams;
import br.com.base_rest_api.domain.dto.response.cliente.ClientePesquisaResponse;
import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;
import br.com.base_rest_api.domain.entity.Cliente;
import br.com.base_rest_api.exception.EntidadeNaoEncontradaException;
import br.com.base_rest_api.mapper.ClienteMapper;
import br.com.base_rest_api.repository.cliente.ClienteRepository;
import br.com.base_rest_api.repository.cliente.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper;

    public Page<ClientePesquisaResponse> pesquisarClientes(ClienteRequestParams clienteRequestParams, Pageable pageable) {
        ClienteSpecification clienteSpecification = new ClienteSpecification(clienteRequestParams);
        Page<Cliente> clientePage = this.clienteRepository.findAll(clienteSpecification, pageable);
        return clientePage.map(this.clienteMapper::converterParaClientePesquisaResponse);
    }

    public ClienteResponse obterCliente(Long idCliente) {
        Cliente cliente = obterClientePorId(idCliente);
        return this.clienteMapper.converterParaClienteResponse(cliente);
    }

    @Transactional
    public ClienteResponse criarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = this.clienteMapper.converterParaCliente(clienteRequest);
        cliente = this.clienteRepository.save(cliente);
        return this.clienteMapper.converterParaClienteResponse(cliente);
    }

    @Transactional
    public ClienteResponse atualizarCliente(Long idCliente, ClienteRequest clienteRequest) {
        Cliente cliente = obterClientePorId(idCliente);
        this.clienteMapper.converterParaCliente(cliente, clienteRequest);
        cliente = this.clienteRepository.save(cliente);
        return this.clienteMapper.converterParaClienteResponse(cliente);
    }

    @Transactional
    public void removerCliente(Long idCliente) {
        this.clienteRepository.deleteById(idCliente);
    }

    @Transactional
    public void removerClientes(List<Long> idClienteList) {
        this.clienteRepository.deleteAllById(idClienteList);
    }

    public Cliente obterClientePorId(Long idCliente) {
        return this.clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado", idCliente));
    }

}