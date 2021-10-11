package br.com.anderson.southsystem.desafiobackvotos.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoPossibilidadeVotacaoVO;

public class InfoAssociadoRest {
	
	public static AssociadoPossibilidadeVotacaoVO getInfoAssociadoPossibilidadeVotacao(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://user-info.herokuapp.com/users/";
		ResponseEntity<AssociadoPossibilidadeVotacaoVO> response = restTemplate.getForEntity(url + cpf, AssociadoPossibilidadeVotacaoVO.class);
		return response.getBody();
	}

}
