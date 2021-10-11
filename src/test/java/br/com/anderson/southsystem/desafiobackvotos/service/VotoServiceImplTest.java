package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

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
import br.com.anderson.southsystem.desafiobackvotos.repository.AssociadoRepository;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;
import br.com.anderson.southsystem.desafiobackvotos.rest.InfoAssociadoRest;
import br.com.anderson.southsystem.desafiobackvotos.validations.VotoBusinessValidation;
import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoPossibilidadeVotacaoVO;

/**
 * Classe de testes para <code>VotoServiceImpl</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public class VotoServiceImplTest extends AbstractSpringBootTest {
	
	@Autowired
	private VotoServiceImpl votoService;
	
	@Autowired
	private AssociadoServiceImpl associadoService;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
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
	
	@Test
	public void deveriaLancarUmaExceptionCasoASessaoNaoSejaEncontrada() throws DesafioBackVotosException {
		// id sessao votacao inexistente
		Long idSessaoInexistente = 80L;
		
		VotoDTO votoDTO = new VotoDTO(null, idSessaoInexistente, OpcaoVotoEnum.SIM);
		
		try {
			votoService.salvar(votoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("Sessao Votacao nao encontrada com o id 80", e.getMessage());
		}
	}
	
	@Test
	public void deveriaLancarUmaExceptionCasoASessaoVotacaoEstejaEncerrada() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoEncerrada());
		
		VotoDTO votoDTO = new VotoDTO(null, 1L, OpcaoVotoEnum.SIM);
		
		VotoServiceImpl votoService = new VotoServiceImpl(mockSessaoVotacaoRepository, null, null);
		
		try {
			votoService.salvar(votoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("Sessão encerrada, não é possivel votar!", e.getMessage());
		}
	}
	
	private Optional<SessaoVotacao> criaMockSessaoVotacaoEncerrada() {
		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		sessaoVotacao.encerra();
		return Optional.of(sessaoVotacao);
	}
	
	@Test
	public void deveriaLancarUmaExceptionCasoOAssociadoNaoSejaEncontrado() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoAberta());
		
		// id associado inexistente
		Long idAssociadoInexistente = 80L;
		
		VotoServiceImpl votoService = new VotoServiceImpl(mockSessaoVotacaoRepository, associadoRepository, null);
		
		VotoDTO votoDTO = new VotoDTO(idAssociadoInexistente, 1L, OpcaoVotoEnum.SIM);
		
		try {
			votoService.salvar(votoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("Associado nao encontrado com o id 80", e.getMessage());
		}
	}
	
	private Optional<SessaoVotacao> criaMockSessaoVotacaoAberta() {
		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		return Optional.of(sessaoVotacao);
	}
	
	@Test
	public void deveriaLancarUmaExceptionCasoAssociadoJaTenhaVotado() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoJaComVotosDoAssociado());
		
		AssociadoRepository mockAssociadoRepository = Mockito.mock(AssociadoRepository.class);
		Mockito.when(mockAssociadoRepository.findById(1L)).thenReturn(criaMockAssociado());
		
		VotoServiceImpl votoService = new VotoServiceImpl(mockSessaoVotacaoRepository, mockAssociadoRepository, null);
		
		VotoDTO votoDTO = new VotoDTO(1L, 1L, OpcaoVotoEnum.SIM);
		
		try {
			votoService.salvar(votoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("O associado de id 1 já votou para essa pauta!", e.getMessage());
		}
	}
	
	@Test
	public void deveriaLancarUmaExceptionCasoAssociadoNaoTenhaPermissaoParaVotar() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoAberta());
		
		AssociadoRepository mockAssociadoRepository = Mockito.mock(AssociadoRepository.class);
		Mockito.when(mockAssociadoRepository.findById(1L)).thenReturn(criaMockAssociado());
		
		InfoAssociadoRest mockInfoAssociadoRest = Mockito.mock(InfoAssociadoRest.class);
		Mockito.when(mockInfoAssociadoRest.getInfoAssociadoPossibilidadeVotacao("35461848826")).thenReturn(criaMockAssociadoPossibilidadeVotacaoVO());
		VotoBusinessValidation votoBusinessValidation = new VotoBusinessValidation(mockInfoAssociadoRest);
		
		VotoServiceImpl votoService = new VotoServiceImpl(mockSessaoVotacaoRepository, mockAssociadoRepository, votoBusinessValidation);
		
		VotoDTO votoDTO = new VotoDTO(1L, 1L, OpcaoVotoEnum.SIM);
		
		try {
			votoService.salvar(votoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("O Associado de cpf 35461848826, não tem permissao para votar!", e.getMessage());
		}
	}
	
	private AssociadoPossibilidadeVotacaoVO criaMockAssociadoPossibilidadeVotacaoVO() {
		AssociadoPossibilidadeVotacaoVO associadoPossibilidadeVotacaoVO = new AssociadoPossibilidadeVotacaoVO();
		associadoPossibilidadeVotacaoVO.setStatus("UNABLE_TO_VOTE");
		return associadoPossibilidadeVotacaoVO;
	}
	
	private Optional<Associado> criaMockAssociado() {
		Associado associado = new Associado();
		associado.setId(1L);
		associado.setCpf("35461848826");
		return Optional.of(associado);
	}
	
	private Optional<SessaoVotacao> criaMockSessaoVotacaoJaComVotosDoAssociado() {
		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		List<Voto> votos = new ArrayList<>();
		Voto voto = new Voto(false, criaMockAssociado().get(), sessaoVotacao);
		votos.add(voto);
		sessaoVotacao.setVotos(votos);
		return Optional.of(sessaoVotacao);
	}

}
