package br.com.anderson.southsystem.desafiobackvotos.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;

@Component
@EnableScheduling
public class TaskFechamentoSessaoVotacao {
	
	private static final Logger log = LoggerFactory.getLogger(TaskFechamentoSessaoVotacao.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
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
	@Scheduled(fixedDelay = 30000)
    public void currentTimeJOBScheduler() {
		log.info("Buscando sessoes para encerrar");
		List<SessaoVotacao> sessoesParaEncerrar = sessaoVotacaoRepository.findSessoesParaEncerrar();
		
		sessoesParaEncerrar.forEach(s -> {
			log.info("Encerrando sessao de id " + s.getId() + " cuja data de encerramento é " + s.getDataEncerramento());
			s.encerra();
		});
		sessaoVotacaoRepository.saveAll(sessoesParaEncerrar);
		
		//TODO mandar para a fila
	}

}
