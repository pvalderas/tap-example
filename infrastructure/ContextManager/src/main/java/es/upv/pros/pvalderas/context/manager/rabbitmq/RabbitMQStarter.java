package es.upv.pros.pvalderas.context.manager.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQStarter implements ApplicationRunner{

	@Autowired
	private ApplicationContext context;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			RabbitMQManager rabbit=(RabbitMQManager)context.getBean("rabbitMQManager");
			rabbit.addConsumer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
