package accesscontroller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import accesscontroller.results.*;

@RestController
public class AccessControllerActuator {

	@RequestMapping(value="/alow",
					method=RequestMethod.GET,
					produces="application/json")
	public String allowaccess(){
		String result ="";
/*
		result=restClient.get().uri("https://access.controller.example.com/alow").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation AllowAccess Executed\"}";
		System.out.println("Operation AllowAccess Executed");
		return new AllowAccessResult().parseResult(result);
	}

	@RequestMapping(value="/deny",
					method=RequestMethod.GET,
					produces="application/json")
	public String denyaccess(){
		String result ="";
/*
		result=restClient.get().uri("https://access.controller.example.com/deny").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation DenyAccess Executed\"}";
		System.out.println("Operation DenyAccess Executed");
		return new DenyAccessResult().parseResult(result);
	}

}