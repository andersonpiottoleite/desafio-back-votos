package br.com.anderson.southsystem.desafiobackvotos.message;

/** interface que representa um contrato para consumo de mensagem de <code>ResultadoVotosVO</code>
 * 
 * @author Anderson Piotto
 * @since 11/10/2021
 * @version 1.0.0
 */

public interface ConsumerMessage {
	
	void readMsgResultadoVotos(final String mensagem);

}
