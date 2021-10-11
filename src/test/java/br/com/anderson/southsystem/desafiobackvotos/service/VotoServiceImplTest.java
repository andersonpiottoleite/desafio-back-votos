package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.VotoDTO;
import br.com.anderson.southsystem.desafiobackvotos.enumeracao.OpcaoVotoEnum;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

/**
 * Classe de testes para <code>VotoServiceImpl</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@SpringBootTest
public class VotoServiceImplTest {
	
	@Autowired
	private VotoServiceImpl votoService;
	
	@Autowired
	private AssociadoServiceImpl associadoService;
	
	@Autowired
	private SessaoVotacaoServiceImpl sessaoVotacaoService;
	
	@Autowired
	private PautaServiceImpl pautaServiceImpl;
	
	@Test
	public void deveriaSalvarUmVoto() throws DesafioBackVotosException {
		// associado
		AssociadoDTO associadoDTO = new AssociadoDTO("Anderson Piotto", "35461848826");
		Associado associado = associadoService.salvar(associadoDTO);
		
		// pauta
		PautaDTO pautaDTO = new PautaDTO();
		pautaDTO.setDescricao("Teste");
		Pauta pauta = pautaServiceImpl.salvar(pautaDTO);
		
		// sessao votacao
		LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(10);
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(pauta.getId(), dataEncerramento);
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		
		//voto 
		VotoDTO votoDTO = new VotoDTO(associado.getId(), sessaoVotacao.getId(), OpcaoVotoEnum.SIM);
		Voto voto = votoService.salvar(votoDTO);
		
		assertNotNull(voto.getId());
	}
	
	// TODO testar mais regras de negocio desa classe

}
