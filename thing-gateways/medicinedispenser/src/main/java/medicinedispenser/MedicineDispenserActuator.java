package medicinedispenser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import medicinedispenser.results.*;

@RestController
public class MedicineDispenserActuator {

	@RequestMapping(value="/dispense",
					method=RequestMethod.GET,
					produces="application/json")
	public String dispense(){
		String result ="";
/*
		result=restClient.get().uri("https://medicine.dispenser.example.com/dispense").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Dispense Executed\"}";
		System.out.println("Operation Dispense Executed");
		return new DispenseResult().parseResult(result);
	}

	@RequestMapping(value="/status",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String setstatus(String input){
		String result ="";
/*
		result=restClient.post().uri("https://medicine.dispenser.example.com/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
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
		result=restClient.get().uri("https://medicine.dispenser.example.com/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetStatus Executed\"}";
		System.out.println("Operation GetStatus Executed");
		return new GetStatusResult().parseResult(result);
	}

}