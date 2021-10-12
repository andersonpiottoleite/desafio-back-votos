package br.com.anderson.southsystem.desafiobackvotos.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** Classe responsavel por consumir mensagem de <code>ResultadoVotosVO</code>
 * 
 * @author Anderson Piotto
 * @since 11/10/2021
 * @version 1.0.0
 */

@Component
public class ConsumerMessageImpl implements ConsumerMessage {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumerMessageImpl.class);
	
	//@KafkaListener(topics = "${topic.name.consumer}")
	public void readMsgResultadoVotos(final String mensagem) {
		log.info("Lendo mensagem do total de votos da sessaoVotacao");
		log.info("Mensagem do total de votos da sessaoVotacao " + mensagem);
	}

}
