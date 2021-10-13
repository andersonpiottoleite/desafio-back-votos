package br.com.anderson.southsystem.desafiobackvotos.jmeter;

import java.io.IOException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.core.io.ClassPathResource;

/**
 * Classe que realiza testes com o JMeter mostra o numero de threads, media de
 * tempo de chamada, se houve erro na chamada etc.
 * 
 * @author Anderson Piotto
 * @since 10/10/2021
 * @version 1.0.0
 */

public class JMeterTestPerformance {

	public static void main(String[] argv) throws Exception {
		doisUsuariosRealizamCemChamadasCada();
	}

	private static void doisUsuariosRealizamCemChamadasCada() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("jmeter.properties");
		JMeterUtils.loadJMeterProperties(classPathResource.getFile().getPath());

		// Engine do JMeter
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		HashTree testPlanTree = new HashTree();

		// Testador HTTP
		HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
		examplecomSampler.setDomain("southsystem-desafio-voto.herokuapp.com");
		examplecomSampler.setPort(80);
		examplecomSampler.setPath("/southsystem-desafio-voto/v1/pauta/1");
		examplecomSampler.setMethod("GET");
		examplecomSampler.setName("Chamando southsystem-desafio-voto.herokuapp.com");

		LoopController loopController = new LoopController();
		loopController.setLoops(2);
		loopController.setFirst(true);

		// Grupo de Threads
		SetupThreadGroup threadGroup = new SetupThreadGroup();
		threadGroup.setName("Grupo de Threads");
		threadGroup.setNumThreads(100);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController(loopController);

		// Plano de testes
		TestPlan testPlan = new TestPlan("Plano de testes JMeter");
		testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

		// Constrtroi Plano de testes com os elementos criados anteriormente
		testPlanTree.add(testPlan);
		HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
		threadGroupHashTree.add(examplecomSampler);

		// Sumario com resultado dos testes, exemplo de 200 chamadas, sem erros:
		// summary = 200 in 00:00:03 = 74,7/s Avg: 766 Min: 332 Max: 1764 Err: 0 (0,00%)
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		ResultCollector logger = new ResultCollector(summer);
		testPlanTree.add(testPlanTree.getArray()[0], logger);

		// Rodando o plano de testes
		jmeter.configure(testPlanTree);
		jmeter.run();
	}

}
