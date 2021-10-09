package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

/**
 * Classe de testes para <code>SessaoVotacaoServiceImpl</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@SpringBootTest
public class SessaoVotacaoServiceImplTest {

	@Autowired
	private SessaoVotacaoServiceImpl sessaoVotacaoService;
	
	@Autowired
	private PautaServiceImpl pautaServiceImpl;

	@Test
	void deveriaAbrirUmaSessaoVotacao() throws DesafioBackVotosException {
		PautaDTO pautaDTO = new PautaDTO();
		pautaDTO.setDescricao("Teste");
		Pauta pauta = pautaServiceImpl.salvar(pautaDTO);
		
		LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(10);
		
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(pauta.getId(), dataEncerramento);
		
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		assertNotNull(sessaoVotacao.getId());
	}
	
	@Test
	void naoDeveriaSalvarUmaSessaoVotacaoComIdPautaInexistenteOuNulo() throws DesafioBackVotosException {
		LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(10);
		
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(10L, dataEncerramento);
		
		assertThrows(DesafioBackVotosException.class, () -> {
			sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		}, 
		"Pauta não encontrada com o id 80");
		
		SessaoVotacaoDTO sessaoVotacaoDTOComIdPautaNulo = new SessaoVotacaoDTO(null, dataEncerramento);
		
		assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			sessaoVotacaoService.abrir(sessaoVotacaoDTOComIdPautaNulo);
		});
	}
	
	@Test
	void naoDeveriaSalvarUmaSessaoVotacaoComDataEncerramentoAnteriorADataAtual() throws DesafioBackVotosException {
		PautaDTO pautaDTO = new PautaDTO();
		pautaDTO.setDescricao("Teste");
		Pauta pauta = pautaServiceImpl.salvar(pautaDTO);
		
		LocalDateTime dataEncerramento = LocalDateTime.now().minusMinutes(10);
		
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(pauta.getId(), dataEncerramento);
		
		assertThrows(DesafioBackVotosException.class, () -> {
			sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		}, 
		"A data de encerramento não pode ser anterior a data atual");
	}

	

}
