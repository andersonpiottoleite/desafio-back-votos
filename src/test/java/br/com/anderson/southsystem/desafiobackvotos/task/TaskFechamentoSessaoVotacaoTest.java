package br.com.anderson.southsystem.desafiobackvotos.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.anderson.southsystem.desafiobackvotos.AbstractSpringBootTest;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;

/**
 * Classe de testes para <code>TaskFechamentoSessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 10/10/2021
 * @version 1.0.0
 */

public class TaskFechamentoSessaoVotacaoTest extends AbstractSpringBootTest {
	
	@Test
	void deveriaFecharSessoesAbertasEComDataEncerramentoMenosQueAgora() throws DesafioBackVotosException {
		SessaoVotacaoRepository mockSessaoVotacaoRepository = Mockito.mock(SessaoVotacaoRepository.class);
		List<SessaoVotacao> listaDeSessoesParaEncerrar = criaMockListaDeSessoesParaEncerrar();
		Mockito.when(mockSessaoVotacaoRepository.findSessoesNaoEncerradas()).thenReturn(listaDeSessoesParaEncerrar);
		
		TaskFechamentoSessaoVotacao taskFechamentoSessaoVotacao = new TaskFechamentoSessaoVotacao(mockSessaoVotacaoRepository);
		
		taskFechamentoSessaoVotacao.currentTimeJOBScheduler();
		
		Assertions.assertEquals(listaDeSessoesParaEncerrar.get(0).isEncerrada(), Boolean.TRUE);
		Assertions.assertEquals(listaDeSessoesParaEncerrar.get(1).isEncerrada(), Boolean.TRUE);
	}

	private List<SessaoVotacao> criaMockListaDeSessoesParaEncerrar() {
		List<SessaoVotacao> sessoesParaEncerrar = new ArrayList<>();
		SessaoVotacao sessaoParaEncerrar1 = new SessaoVotacao();
		sessaoParaEncerrar1.setDataEncerramento(LocalDateTime.now().minusMinutes(10));
		SessaoVotacao sessaoParaEncerrar2 = new SessaoVotacao();
		sessaoParaEncerrar2.setDataEncerramento(LocalDateTime.now().minusMinutes(10));
		sessoesParaEncerrar.add(sessaoParaEncerrar1);
		sessoesParaEncerrar.add(sessaoParaEncerrar2);
		return sessoesParaEncerrar;
	}

}
