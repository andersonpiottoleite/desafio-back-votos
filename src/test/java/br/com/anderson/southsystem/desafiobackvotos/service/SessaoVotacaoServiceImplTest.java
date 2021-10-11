package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;
import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;

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
	void casoNaoSejaInformadoDataDeEncerramentoElaDeveriaSerDeUmMinutoSuperiorADataDeAgora() throws DesafioBackVotosException {
		PautaDTO pautaDTO = new PautaDTO();
		pautaDTO.setDescricao("Teste");
		Pauta pauta = pautaServiceImpl.salvar(pautaDTO);
		
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(pauta.getId(), null);
		
		LocalDateTime momentoAntesDaCriacaoDataDefaut = LocalDateTime.now();
		
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		
		assertNotNull(sessaoVotacao.getId());
				
		int minutoDataDefault = sessaoVotacao.getDataEncerramento().getMinute();
		int minutoAntesDaCriacaoDataDefaut = momentoAntesDaCriacaoDataDefaut.getMinute();
		int diferenca = (minutoDataDefault - minutoAntesDaCriacaoDataDefaut);
		
		assertThat(diferenca == 1);
	}
	
	@Test
	void naoDeveriaSalvarUmaSessaoVotacaoComIdPautaInexistenteOuNulo() throws DesafioBackVotosException {
		LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(10);
		
		SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO(10L, dataEncerramento);
		
		try {
			sessaoVotacaoService.abrir(sessaoVotacaoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("Pauta não encontrada com o id 10", e.getMessage());
		}
		
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
		
		try {
			sessaoVotacaoService.abrir(sessaoVotacaoDTO);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("A data de encerramento não pode ser anterior a data atual", e.getMessage());
		}
	}
	
	@Test
	void deveriaLancarUmaExceptionCasoNaoHajaVotosParaSeremContabilizados() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoSemVotos());
		
		SessaoVotacaoServiceImpl sessaoVotacaoService = new SessaoVotacaoServiceImpl(mockSessaoVotacaoRepository);
		
		try {
			sessaoVotacaoService.contabilizarVotos(1L);
			Assertions.fail();
		} catch (DesafioBackVotosException e) {
			assertEquals("Sessao sem votos para serem contabilizados", e.getMessage());
		}
	}
	
	@Test
	void deveriaContabilizarCorretamenteOsVotosRealizadosContabilizados() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		Mockito.when(mockSessaoVotacaoRepository.findById(1L)).thenReturn(criaMockSessaoVotacaoComVotos());
		
		SessaoVotacaoServiceImpl sessaoVotacaoService = new SessaoVotacaoServiceImpl(mockSessaoVotacaoRepository);
		
		ResultadoVotosVO resultadoVotosVO = sessaoVotacaoService.contabilizarVotos(1L);
		
		assertThat(resultadoVotosVO.getQuantidadeTotalVotos() == 3);
		assertThat(resultadoVotosVO.getQuantidadeVotosAFavor() == 1);
		assertThat(resultadoVotosVO.getQuantidadeVotosContra() == 2);
	}
	
	private Optional<SessaoVotacao> criaMockSessaoVotacaoSemVotos() {
		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		return Optional.of(sessaoVotacao);
	}
	
	private Optional<SessaoVotacao> criaMockSessaoVotacaoComVotos() {
		Pauta pauta = new Pauta("Lula inocente?");
		
		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		sessaoVotacao.setPauta(pauta);
		
		Voto voto1 = new Voto(true, null, null);
		Voto voto2 = new Voto(false, null, null);
		Voto voto3 = new Voto(false, null, null);
		List<Voto> votos = List.of(voto1, voto2, voto3);
		
		sessaoVotacao.setVotos(votos);
		
		return Optional.of(sessaoVotacao);
	}
	

}
