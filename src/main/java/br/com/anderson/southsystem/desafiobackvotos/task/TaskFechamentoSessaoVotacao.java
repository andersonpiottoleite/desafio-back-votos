package br.com.anderson.southsystem.desafiobackvotos.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;

@Component
@EnableScheduling
public class TaskFechamentoSessaoVotacao {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	/**
	 * a cada 30 segundos, verifica se existem sessoes para encerrar
	 */
	@Scheduled(fixedDelay = 30000)
    public void currentTimeJOBScheduler() {
		System.out.println("Buscando sessoes para encerrar");
		List<SessaoVotacao> sessoesParaEncerrar = sessaoVotacaoRepository.findSessoesParaEncerrar();
		
		sessoesParaEncerrar.forEach(s -> {
			System.out.println("Encerrando sessao de id " + s.getId() + " cuja data de encerramento é " + s.getDataEncerramento());
			s.encerra();
		});
		sessaoVotacaoRepository.saveAll(sessoesParaEncerrar);
		//TODO mandar para a fila
	}

}
