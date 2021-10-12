package br.com.anderson.southsystem.desafiobackvotos.message;

import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;



/** interface que representa um contrato para envio de mensagem de <code>ResultadoVotosVO</code>
 * 
 * @author Anderson Piotto
 * @since 11/10/2021
 * @version 1.0.0
 */

public interface ProducerMessage {
	
	void sendMsgResultadoVotos(ResultadoVotosVO resultado);

}
