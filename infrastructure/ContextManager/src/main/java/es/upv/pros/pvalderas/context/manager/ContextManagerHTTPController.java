package es.upv.pros.pvalderas.context.manager;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.upv.pros.pvalderas.http.HTTPClient;

@RestController
public class ContextManagerHTTPController {
	
	@RequestMapping(
			  value = "/context/executions/{user}/{device}/{operation}", 
			  method = RequestMethod.GET,
			  consumes = "application/json")
	public JSONObject getNumExecutions(@PathVariable String device, @PathVariable String operation, @PathVariable String user) throws JSONException, IOException{
    	return getContext(device, operation, user, ContextManagerAPI.NUM_EXECUTIONS);
	}
	
	
	
	
	private JSONObject getContext(String device, String operation, String user, String property) throws JSONException, IOException{
		String url="https://pedvalar.webs.upv.es/microservices/context/"+device+"/"+operation+"/"+user+"/"+property;
    	JSONObject context=new JSONObject(HTTPClient.get(url));
    	return context;
	}
	
}
