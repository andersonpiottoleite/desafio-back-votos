package br.com.anderson.southsystem.desafiobackvotos.task;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.message.ProducerMessageImpl;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;
import br.com.anderson.southsystem.desafiobackvotos.service.SessaoVotacaoServiceImpl;
import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;

/** Classe que representa uma task, que realiza o encerramento da <code>SessaoVotacao</code>, caso a data da encerramento tenha chegado
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Component
@EnableScheduling
public class TaskFechamentoSessaoVotacao {
	
	private static final Logger log = LoggerFactory.getLogger(TaskFechamentoSessaoVotacao.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private SessaoVotacaoServiceImpl sessaoVotacaoService;
	
	//@Autowired
	//private ProducerMessageImpl producerMessageImpl;
	
	public TaskFechamentoSessaoVotacao(){}
	
	/** Construtor para ser usado em mock de testes unitarios
	 * 
	 * @param sessaoVotacaoRepository
	 */
	public TaskFechamentoSessaoVotacao(SessaoVotacaoRepository sessaoVotacaoRepository){
		this.sessaoVotacaoRepository = sessaoVotacaoRepository;
	}
	
	/**
	 * a cada 30 segundos, verifica se existem sessoes para encerrar
	 */
	@Transactional
	@Scheduled(fixedDelay = 30000)
    public void currentTimeJOBScheduler() {
		log.info("Buscando sessoes para encerrar");
		List<SessaoVotacao> sessoesParaEncerrar = sessaoVotacaoRepository.findSessoesParaEncerrar();
		
		if (sessoesParaEncerrar.isEmpty()) {
			return;
		}
		
		sessoesParaEncerrar.forEach(s -> {
			log.info("Encerrando sessao de id " + s.getId() + " cuja data de encerramento é " + s.getDataEncerramento());
			s.encerra();
		});
		sessaoVotacaoRepository.saveAll(sessoesParaEncerrar);
		
		//contabilizaVotosEEnviaMensagem(sessoesParaEncerrar);
	}

	private void contabilizaVotosEEnviaMensagem(List<SessaoVotacao> sessoesParaEncerrar) {
		sessoesParaEncerrar.forEach(c -> {			
			ResultadoVotosVO resultado = null;
			try {
				resultado = sessaoVotacaoService.contabilizarVotos(c.getId());
			} catch (DesafioBackVotosException e) {
				log.error("Erro ao contabilizar votos durante o encerramento da sessaoVotacao " + e.getMessage());
			}
			if (Objects.nonNull(resultado)) {				
				//producerMessageImpl.sendMsgResultadoVotos(resultado);
			}
		});
	}

}
