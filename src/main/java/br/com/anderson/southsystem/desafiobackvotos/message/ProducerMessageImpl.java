package br.com.anderson.southsystem.desafiobackvotos.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;

/** Classe responsavel por enviar mensagem de <code>ResultadoVotosVO</code>
 * 
 * @author Anderson Piotto
 * @since 11/10/2021
 * @version 1.0.0
 */

@Component
public class ProducerMessageImpl implements ProducerMessage {
	
	private static final Logger log = LoggerFactory.getLogger(ProducerMessageImpl.class);

   // @Value("${topic.name.producer}")
    private String topicName;
	
	private final ObjectMapper objectMapper = new ObjectMapper(); 
	
	//@Autowired
	//private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMsgResultadoVotos(ResultadoVotosVO resultado) {
		log.info("Enviando mensagem do total de votos da sessaoVotacao " + resultado.getIdSessao());
		
		String resultadoString;
		try {
			resultadoString = objectMapper.writeValueAsString(resultado);
			//kafkaTemplate.send(topicName, resultadoString);
			log.info("Mensagem do total de votos da sessaoVotacao {} enviada com sucesso!" + resultado.getIdSessao());
		} catch (Exception e) {
			log.error("Erro ao enviar a mensagem do total de votos da sessaoVotacao" + resultado.getIdSessao());
		}
	}

}
