package br.com.base_rest_api.mapper;

import br.com.base_rest_api.domain.dto.request.venda.ProdutoVendaRequest;
import br.com.base_rest_api.domain.dto.request.venda.VendaRequest;
import br.com.base_rest_api.domain.dto.response.venda.VendaResponse;
import br.com.base_rest_api.domain.entity.Cliente;
import br.com.base_rest_api.domain.entity.Produto;
import br.com.base_rest_api.domain.entity.ProdutoVenda;
import br.com.base_rest_api.domain.entity.Venda;
import br.com.base_rest_api.service.ClienteService;
import br.com.base_rest_api.service.ProdutoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class VendaMapper {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public abstract VendaResponse converterParaVendaResponse(Venda venda);

    @Mapping(target = "cliente", expression = "java(converterParaCliente(vendaRequest.idCliente()))")
    @Mapping(target = "produtosVenda", expression = "java(converterParaProdutoVendaList(venda, vendaRequest.produtosVenda()))")
    public abstract Venda converterParaVenda(VendaRequest vendaRequest);

    public void converterParaVenda(Venda venda, VendaRequest vendaRequest) {
        if (vendaRequest == null) {
            return;
        }

        venda.setDataEmissao(vendaRequest.dataEmissao());
        venda.setValorTotal(vendaRequest.valorTotal());

        Cliente cliente = converterParaCliente(vendaRequest.idCliente());
        venda.setCliente(cliente);

        List<ProdutoVenda> produtoVendaList = converterParaProdutoVendaList(venda, vendaRequest.produtosVenda());
        if (venda.getProdutosVenda() != null) {
            venda.getProdutosVenda().clear();
            if (produtoVendaList != null) {
                venda.getProdutosVenda().addAll(produtoVendaList);
            }
        } else {
            if (produtoVendaList != null) {
                venda.setProdutosVenda(produtoVendaList);
            }
        }
    }

    public Cliente converterParaCliente(Long idCliente) {
        return this.clienteService.obterClientePorId(idCliente);
    }

    public List<ProdutoVenda> converterParaProdutoVendaList(Venda venda, List<ProdutoVendaRequest> produtoVendaRequestList) {
        if (produtoVendaRequestList == null) {
            return null;
        }

        List<ProdutoVenda> produtoVendaList = new ArrayList<>();
        for (ProdutoVendaRequest produtoVendaRequest : produtoVendaRequestList) {
            ProdutoVenda produtoVenda = converterParaProdutoVenda(produtoVendaRequest);
            Produto produto = converterParaProduto(produtoVendaRequest.idProduto());
            produtoVenda.setProduto(produto);
            produtoVenda.setVenda(venda);
            produtoVendaList.add(produtoVenda);
        }

        return produtoVendaList;
    }

    public abstract ProdutoVenda converterParaProdutoVenda(ProdutoVendaRequest produtoVendaRequest);

    public Produto converterParaProduto(Long idProduto) {
        return this.produtoService.obterProdutoPorId(idProduto);
    }

}