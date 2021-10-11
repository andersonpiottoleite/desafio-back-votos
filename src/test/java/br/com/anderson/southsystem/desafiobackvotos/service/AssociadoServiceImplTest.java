package br.com.anderson.southsystem.desafiobackvotos.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.anderson.southsystem.desafiobackvotos.AbstractSpringBootTest;
import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;

/**
 * Classe de testes para <code>AssociadoServiceImpl</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public class AssociadoServiceImplTest extends AbstractSpringBootTest {
	
	@Autowired
	private AssociadoServiceImpl associadoService;
	
	@Test
	public void deveriaCadastrarUmAssociado() {
		AssociadoDTO associadoDTO = new AssociadoDTO("Anderson Piotto", "35461848826");
		Associado associado = associadoService.salvar(associadoDTO);
		Assertions.assertNotNull(associado.getId());
	}

}
