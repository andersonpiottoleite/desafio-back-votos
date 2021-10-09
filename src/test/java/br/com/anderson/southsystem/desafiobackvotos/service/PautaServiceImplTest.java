package br.com.anderson.southsystem.desafiobackvotos.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
public class PautaServiceImplTest {

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

	@Test
	void naoDeveriaSalvarUmaPautaComDescricaoVazia() throws DesafioBackVotosException {
		PautaDTO pautaDTO = new PautaDTO("");
		assertThrows(DesafioBackVotosException.class, () -> {
			pautaService.salvar(pautaDTO);
		}, 
		"A descrição da Pauta não pode ser nula ou vazia");
	}

	// TODO, testar o outro metodo de contabilizar votos que existe com o mockito

}
