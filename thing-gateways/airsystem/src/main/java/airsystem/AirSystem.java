package airsystem;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class AirSystem {
	public static void main(String[] args) {
		SpringApplication.run(AirSystem.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
	    return args -> {
			String microserviceName=environment.getProperty("spring.application.name");
			String ip=environment.getProperty("server.address");
			String port=environment.getProperty("server.port");
			RestClient restClient = RestClient.create();
			restClient.put().uri("https://pedvalar.webs.upv.es/microservices/host")
				.contentType(MediaType.APPLICATION_JSON)
				.body(new String("{\"name\":\""+microserviceName+"\",\"ip\":\""+ip+"\",\"port\":"+port+"}"))
				.retrieve().toBodilessEntity();
	    };
	}
}
