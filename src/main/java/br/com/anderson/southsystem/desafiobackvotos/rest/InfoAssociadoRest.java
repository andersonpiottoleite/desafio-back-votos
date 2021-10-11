package br.com.anderson.southsystem.desafiobackvotos.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoPossibilidadeVotacaoVO;

/** Classe que faz a integração com um serviço de associados, que expoe se é permitido que o associado vote
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Component
public class InfoAssociadoRest {
	
	private static final Logger log = LoggerFactory.getLogger(InfoAssociadoRest.class);
	
	public AssociadoPossibilidadeVotacaoVO getInfoAssociadoPossibilidadeVotacao(String cpf) {
		log.info("Consultando servico: https://user-info.herokuapp.com/users/" + cpf);
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://user-info.herokuapp.com/users/";
		ResponseEntity<AssociadoPossibilidadeVotacaoVO> response = restTemplate.getForEntity(url + cpf, AssociadoPossibilidadeVotacaoVO.class);
		log.info("Retorno do serviço: " + response.getBody());
		return response.getBody();
	}

}
