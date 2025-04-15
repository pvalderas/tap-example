package celinglight;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import celinglight.results.*;

@RestController
public class CelingLightActuator {

	@RequestMapping(value="/turn/on",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnon(){
		String result ="";
/*
		result=restClient.get().uri("https://celinglight.example.com/turn/on").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation TurnOn Executed\"}";
		System.out.println("Operation TurnOn Executed");
		return new TurnOnResult().parseResult(result);
	}

	@RequestMapping(value="/turn/off",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnoff(){
		String result ="";
/*
		result=restClient.get().uri("https://celinglight.example.com/turn/off").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation TurnOff Executed\"}";
		System.out.println("Operation TurnOff Executed");
		return new TurnOffResult().parseResult(result);
	}

	@RequestMapping(value="/status",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String setstatus(String input){
		String result ="";
/*
		result=restClient.post().uri("https://celinglight.example.com/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
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
		result=restClient.get().uri("https://celinglight.example.com/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetStatus Executed\"}";
		System.out.println("Operation GetStatus Executed");
		return new GetStatusResult().parseResult(result);
	}

}