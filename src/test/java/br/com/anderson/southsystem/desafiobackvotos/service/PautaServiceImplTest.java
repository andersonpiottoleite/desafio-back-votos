package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.anderson.southsystem.desafiobackvotos.AbstractSpringBootTest;
import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.repository.PautaRepository;

/**
 * Classe de testes para <code>PautaServiceImpl</code>
 * 
 * @author Anderson Piotto
 * @since 08/10/2021
 * @version 1.0.0
 */

public class PautaServiceImplTest extends AbstractSpringBootTest {

	@Autowired
	private PautaServiceImpl pautaService;
	
	@Mock
	private PautaRepository pautaRepository;

	@Test
	void deveriaSalvarUmaPauta() throws DesafioBackVotosException {
		PautaDTO pautaDTO = new PautaDTO("Devemos ter voto Impresso nas Eleições 2022?");
		Pauta pauta = pautaService.salvar(pautaDTO);
		assertNotNull(pauta.getId());
	}

}
