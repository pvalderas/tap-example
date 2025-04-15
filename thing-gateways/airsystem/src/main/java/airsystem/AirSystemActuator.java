package airsystem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class AirSystemActuator {

	@RequestMapping(value="/turnon",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnon(){
		String result ="";
/*
		result=restClient.get().uri("https://airsystem.example.com/turnon").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Turnon Executed\"}";
		System.out.println("Operation Turnon Executed");
		return new TurnonResult().parseResult(result);
	}

	@RequestMapping(value="/turnoff",
					method=RequestMethod.GET,
					produces="application/json")
	public String turnoff(){
		String result ="";
/*
		result=restClient.get().uri("https://airsystem.example.com/turnoff").retrieve().body(String.class);
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
		result=restClient.post().uri("https://airsystem.example.com/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
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
		result=restClient.get().uri("https://airsystem.example.com/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetStatus Executed\"}";
		System.out.println("Operation GetStatus Executed");
		return new GetStatusResult().parseResult(result);
	}

	@RequestMapping(value="/temperature",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String settemperature(String input){
		String result ="";
/*
		result=restClient.post().uri("https://airsystem.example.com/temperature").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
*/
		result="{\"message\":\"Operation SetTemperature Executed\"}";
		System.out.println("Operation SetTemperature Executed");
		return new SetTemperatureResult().parseResult(result);
	}

	@RequestMapping(value="/temperature",
					method=RequestMethod.GET,
					produces="application/json")
	public String gettemperature(){
		String result ="";
/*
		result=restClient.get().uri("https://airsystem.example.com/temperature").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetTemperature Executed\"}";
		System.out.println("Operation GetTemperature Executed");
		return new GetTemperatureResult().parseResult(result);
	}

}