package br.com.anderson.southsystem.desafiobackvotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

/** interface que representa um repository <code>Pauta</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
