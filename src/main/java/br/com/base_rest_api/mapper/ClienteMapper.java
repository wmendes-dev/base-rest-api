package br.com.base_rest_api.mapper;

import br.com.base_rest_api.domain.dto.request.cliente.ClienteContatoRequest;
import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequest;
import br.com.base_rest_api.domain.dto.response.cliente.ClientePesquisaResponse;
import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;
import br.com.base_rest_api.domain.entity.Cliente;
import br.com.base_rest_api.domain.entity.ClienteContato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {

    public abstract ClientePesquisaResponse converterParaClientePesquisaResponse(Cliente cliente);

    public abstract ClienteResponse converterParaClienteResponse(Cliente cliente);

    @Mapping(target = "contatos", expression = "java(converterParaClienteContatoList(cliente, clienteRequest.contatos()))")
    public abstract Cliente converterParaCliente(ClienteRequest clienteRequest);

    public void converterParaCliente(Cliente cliente, ClienteRequest clienteRequest) {
        if (clienteRequest == null) {
            return;
        }

        cliente.setTipoPessoa(clienteRequest.tipoPessoa());
        cliente.setCpfCnpj(clienteRequest.cpfCnpj());
        cliente.setNomeRazaoSocial(clienteRequest.nomeRazaoSocial());
        cliente.setCep(clienteRequest.cep());
        cliente.setLogradouro(clienteRequest.logradouro());
        cliente.setNumero(clienteRequest.numero());
        cliente.setComplemento(clienteRequest.complemento());
        cliente.setBairro(clienteRequest.bairro());
        cliente.setCidade(clienteRequest.cidade());
        cliente.setTipoPessoa(clienteRequest.tipoPessoa());

        List<ClienteContato> clienteContatoList = converterParaClienteContatoList(cliente, clienteRequest.contatos());
        if (cliente.getContatos() != null) {
            cliente.getContatos().clear();
            if (clienteContatoList != null) {
                cliente.getContatos().addAll(clienteContatoList);
            }
        } else {
            if (clienteContatoList != null) {
                cliente.setContatos(clienteContatoList);
            }
        }
    }

    public List<ClienteContato> converterParaClienteContatoList(Cliente cliente, List<ClienteContatoRequest> clienteContatoRequestList) {
        if (clienteContatoRequestList == null) {
            return null;
        }

        List<ClienteContato> clienteContatoList = new ArrayList<>();
        for (ClienteContatoRequest clienteContatoRequest : clienteContatoRequestList) {
            ClienteContato clienteContato = converterParaClienteContato(clienteContatoRequest);
            clienteContato.setCliente(cliente);
            clienteContatoList.add(clienteContato);
        }

        return clienteContatoList;
    }

    public abstract ClienteContato converterParaClienteContato(ClienteContatoRequest clienteContatoRequest);

}