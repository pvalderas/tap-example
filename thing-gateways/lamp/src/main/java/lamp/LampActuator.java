package lamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import lamp.results.GetStatusResult;
import lamp.results.SetStatusResult;
import lamp.results.ToggleResult;
import lamp.results.TurnOnResult;
import lamp.results.TurnoffResult;

@RestController
public class LampActuator {

	public LampActuator(){

	}

	@RequestMapping(value="/toggle",
					method=RequestMethod.GET,
					produces="application/json")
	public String toggle(){
		String result ="";
/*
		result=restClient.get().uri("https://mylamp.example.com/toggle").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Toggle Executed\"}";
		System.out.println("Operation Toggle Executed");
		return new ToggleResult().parseResult(result);
	}

	@RequestMapping(value="/on",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnon(){
		String result ="";
/*
		result=restClient.get().uri("https://mylamp.example.com/on").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation TurnOn Executed\"}";
		System.out.println("Operation TurnOn Executed");
		return new TurnOnResult().parseResult(result);
	}

	@RequestMapping(value="/off",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnoff(){
		String result ="";
/*
		result=restClient.get().uri("https://mylamp.example.com/off").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Turnoff Executed\"}";
		System.out.println("Operation Turnoff Executed");
		return new TurnoffResult().parseResult(result);
	}

	@RequestMapping(value="/status",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String setstatus(String input){
		String result ="";
/*
		result=restClient.post().uri("https://mylamp.example.com/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
*/
		result="{\"message\":\"Operation SetStatus Executed\"}";
		System.out.println("Operation SetStatus Executed");
		return new SetStatusResult().parseResult(result);
	}

	@RequestMapping(value="/status",
					method=RequestMethod.GET,
					produces="application/json")
	public String getstatus(){
		String result ="";
/*
		result=restClient.get().uri("https://mylamp.example.com/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetStatus Executed\"}";
		System.out.println("Operation GetStatus Executed");
		return new GetStatusResult().parseResult(result);
	}


	private void publishExecution(String operation, String User, String process, String instance){

	}


	
}