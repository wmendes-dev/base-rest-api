package br.com.base_rest_api.mapper;

import br.com.base_rest_api.domain.dto.request.produto.ProdutoRequest;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoPesquisaResponse;
import br.com.base_rest_api.domain.dto.response.produto.ProdutoResponse;
import br.com.base_rest_api.domain.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoPesquisaResponse converterParaProdutoPesquisaResponse(Produto produto);

    ProdutoResponse converterParaProdutoResponse(Produto produto);

    Produto converterParaProduto(ProdutoRequest produtoRequest);

    void converterParaProduto(@MappingTarget Produto produto, ProdutoRequest produtoRequest);

}