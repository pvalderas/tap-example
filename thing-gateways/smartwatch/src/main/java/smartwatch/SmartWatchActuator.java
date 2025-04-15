package smartwatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import smartwatch.results.*;

@RestController
public class SmartWatchActuator {

	@RequestMapping(value="/health/params",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String sethealthparams(String input){
		String result ="";
/*
		result=restClient.post().uri("https://smart.watch.example.com/health/params").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
*/
		result="{\"message\":\"Operation SetHealthParams Executed\"}";
		System.out.println("Operation SetHealthParams Executed");
		return new SetHealthParamsResult().parseResult(result);
	}

	@RequestMapping(value="/health/params",
					method=RequestMethod.GET,
					produces="application/json")
	public String gethealthparams(){
		String result ="";
/*
		result=restClient.get().uri("https://smart.watch.example.com/health/params").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetHealthParams Executed\"}";
		System.out.println("Operation GetHealthParams Executed");
		return new GetHealthParamsResult().parseResult(result);
	}

}