package br.com.base_rest_api.mapper;

import br.com.base_rest_api.domain.dto.response.autenticacao.UsuarioResponse;
import br.com.base_rest_api.domain.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse converterParaUsuarioResponse(Usuario usuario);

}