//package br.com.base_rest_api.service;
//
//import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequest;
//import br.com.base_rest_api.domain.dto.request.cliente.ClienteRequestParams;
//import br.com.base_rest_api.domain.dto.response.cliente.ClientePesquisaResponse;
//import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;
//import br.com.base_rest_api.domain.entity.Cliente;
//import br.com.base_rest_api.domain.enums.SituacaoEnum;
//import br.com.base_rest_api.exception.EntidadeNaoEncontradaException;
//import br.com.base_rest_api.mapper.ClienteMapper;
//import br.com.base_rest_api.repository.cliente.ClienteRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//class ClienteServiceTest {
//
//    @Mock
//    private ClienteRepository clienteRepository;
//
//    @Mock
//    private ClienteMapper clienteMapper;
//
//    @InjectMocks
//    private ClienteService clienteService;
//
//    @Test
//    void devePesquisarClientesComSucesso() {
//        ClienteRequestParams clienteRequestParams = new ClienteRequestParams("Cliente 1");
//        Cliente cliente = new Cliente(1L, "12345678910", "Cliente 1", SituacaoEnum.ATIVO);
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Cliente> clientePage = new PageImpl<>(List.of(cliente));
//        ClientePesquisaResponse clientePesquisaResponse = new ClientePesquisaResponse(1L, "Cliente 1", "12345678910", SituacaoEnum.ATIVO);
//
//        Mockito.when(this.clienteRepository.findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.eq(pageable)))
//                .thenReturn(clientePage);
//        Mockito.when(this.clienteMapper.converterParaClientePesquisaResponse(cliente))
//                .thenReturn(clientePesquisaResponse);
//
//        Page<ClientePesquisaResponse> resultado = this.clienteService.pesquisarClientes(clienteRequestParams, pageable);
//
//        Assertions.assertThat(resultado).isNotEmpty();
//        Assertions.assertThat(resultado.getContent()).hasSize(1);
//        Assertions.assertThat(resultado.getContent().getFirst().nome()).isEqualTo("Cliente 1");
//
//        Mockito.verify(this.clienteRepository).findAll(ArgumentMatchers.any(Specification.class), ArgumentMatchers.eq(pageable));
//        Mockito.verify(this.clienteMapper).converterParaClientePesquisaResponse(cliente);
//        Mockito.verifyNoMoreInteractions(this.clienteRepository, this.clienteMapper);
//    }
//
//    @Test
//    void deveObterClienteComSucesso() {
//        Cliente cliente = new Cliente(1L, "12345678910", "Cliente 1", SituacaoEnum.ATIVO);
//        ClienteResponse clienteResponse = new ClienteResponse(1L, "Cliente 1", "12345678910");
//
//        Mockito.when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
//        Mockito.when(this.clienteMapper.converterParaClienteResponse(cliente)).thenReturn(clienteResponse);
//
//        ClienteResponse resultado = this.clienteService.obterCliente(1L);
//
//        Assertions.assertThat(resultado).isNotNull();
//        Assertions.assertThat(resultado.idCliente()).isEqualTo(1L);
//
//        Mockito.verify(this.clienteRepository).findById(1L);
//        Mockito.verify(this.clienteMapper).converterParaClienteResponse(cliente);
//        Mockito.verifyNoMoreInteractions(this.clienteRepository, this.clienteMapper);
//    }
//
//    @Test
//    void deveCriarClienteComSucesso() {
//        ClienteRequest clienteRequest = new ClienteRequest("Cliente 1", "12345678910", SituacaoEnum.ATIVO);
//        Cliente cliente = new Cliente(1L, "12345678910", "Cliente 1", SituacaoEnum.ATIVO);
//        ClienteResponse clienteCriadoResponse = new ClienteResponse(1L, "Cliente 1", "12345678910");
//
//        Mockito.when(this.clienteMapper.converterParaCliente(clienteRequest)).thenReturn(cliente);
//        Mockito.when(this.clienteRepository.save(cliente)).thenReturn(cliente);
//        Mockito.when(this.clienteMapper.converterParaClienteResponse(cliente)).thenReturn(clienteCriadoResponse);
//
//        ClienteResponse resultado = this.clienteService.criarCliente(clienteRequest);
//
//        Assertions.assertThat(resultado).isNotNull();
//        Assertions.assertThat(resultado.idCliente()).isEqualTo(1L);
//
//        Mockito.verify(this.clienteMapper).converterParaCliente(clienteRequest);
//        Mockito.verify(this.clienteRepository).save(cliente);
//        Mockito.verify(this.clienteMapper).converterParaClienteResponse(cliente);
//        Mockito.verifyNoMoreInteractions(this.clienteRepository);
//    }
//
//    @Test
//    void deveAtualizarClienteComSucesso() {
//        Cliente cliente = new Cliente(1L, "12345678910", "Cliente 1", SituacaoEnum.ATIVO);
//        ClienteRequest clienteAtualizadoRequest = new ClienteRequest("Cliente 1 Atualizado", "12345678910", SituacaoEnum.ATIVO);
//        ClienteResponse clienteAtualizadoResponse = new ClienteResponse(1L, "Cliente 1 Atualizado", "12345678910");
//
//        Mockito.when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
//        Mockito.doNothing().when(this.clienteMapper).converterParaCliente(cliente, clienteAtualizadoRequest);
//        Mockito.when(this.clienteRepository.save(cliente)).thenReturn(cliente);
//        Mockito.when(this.clienteMapper.converterParaClienteResponse(cliente)).thenReturn(clienteAtualizadoResponse);
//
//        ClienteResponse resultado = this.clienteService.atualizarCliente(1L, clienteAtualizadoRequest);
//
//        Assertions.assertThat(resultado).isNotNull();
//        Assertions.assertThat(resultado.idCliente()).isEqualTo(1L);
//        Assertions.assertThat(resultado.nome()).isEqualTo("Cliente 1 Atualizado");
//
//        Mockito.verify(this.clienteRepository).findById(1L);
//        Mockito.verify(this.clienteMapper).converterParaCliente(cliente, clienteAtualizadoRequest);
//        Mockito.verify(this.clienteRepository).save(cliente);
//        Mockito.verify(this.clienteMapper).converterParaClienteResponse(cliente);
//        Mockito.verifyNoMoreInteractions(this.clienteRepository);
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
//        Mockito.when(this.clienteRepository.findById(999L)).thenReturn(Optional.empty());
//
//        Assertions.assertThatThrownBy(() -> this.clienteService.obterCliente(999L))
//                .isInstanceOf(EntidadeNaoEncontradaException.class)
//                .hasMessageContaining("Cliente não encontrado");
//
//        Mockito.verify(this.clienteRepository).findById(999L);
//        Mockito.verifyNoMoreInteractions(this.clienteRepository);
//        Mockito.verifyNoInteractions(this.clienteMapper);
//    }
//
//}
