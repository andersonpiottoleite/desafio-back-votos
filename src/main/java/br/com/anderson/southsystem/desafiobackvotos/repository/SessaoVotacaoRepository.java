package br.com.anderson.southsystem.desafiobackvotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>{

	@Query("SELECT s FROM SessaoVotacao s WHERE s.encerrada = 0 AND s.dataEncerramento < CURRENT_TIMESTAMP()")
	List<SessaoVotacao> findSessoesParaEncerrar();
	
}
