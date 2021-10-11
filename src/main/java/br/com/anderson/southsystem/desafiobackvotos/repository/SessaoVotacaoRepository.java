package br.com.anderson.southsystem.desafiobackvotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

/** interface que representa um repository <code>SessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>{

	@Query("SELECT s FROM SessaoVotacao s WHERE s.encerrada = 0 AND s.dataEncerramento < CURRENT_TIMESTAMP()")
	List<SessaoVotacao> findSessoesParaEncerrar();
	
}
