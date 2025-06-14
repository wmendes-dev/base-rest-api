package br.com.base_rest_api.repository.venda;

import br.com.base_rest_api.domain.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>, VendaCustomRepository {

}