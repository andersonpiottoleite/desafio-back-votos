package br.com.anderson.southsystem.desafiobackvotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.southsystem.desafiobackvotos.model.Associado;

/** interface que representa um repository <code>Associado</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface AssociadoRepository extends JpaRepository<Associado, Long>{

}
