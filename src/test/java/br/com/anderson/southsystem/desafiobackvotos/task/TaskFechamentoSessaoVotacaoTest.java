package br.com.anderson.southsystem.desafiobackvotos.task;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;

/**
 * Classe de testes para <code>TaskFechamentoSessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 10/10/2021
 * @version 1.0.0
 */

@SpringBootTest
public class TaskFechamentoSessaoVotacaoTest {
	
	@Test
	void deveriaFecharSessoesAbertasEComDataEncerramentoMenosQueAgora() throws DesafioBackVotosException {
		// TODO  fazer esse teste
		assertNotNull(new Object());
	}

}
