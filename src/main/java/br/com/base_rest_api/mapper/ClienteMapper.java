package br.com.base_rest_api.mapper;

import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequest;
import br.com.base_rest_api.domain.dto.response.cliente.ClientePesquisaResponse;
import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;
import br.com.base_rest_api.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClientePesquisaResponse converterParaClientePesquisaResponse(Cliente cliente);

    ClienteResponse converterParaClienteResponse(Cliente cliente);

    Cliente converterParaCliente(ClienteRequest clienteRequest);

    void converterParaCliente(@MappingTarget Cliente cliente, ClienteRequest clienteRequest);

}