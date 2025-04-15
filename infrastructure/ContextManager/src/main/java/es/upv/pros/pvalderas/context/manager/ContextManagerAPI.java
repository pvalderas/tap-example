package es.upv.pros.pvalderas.context.manager;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import es.upv.pros.pvalderas.http.HTTPClient;

@Component
public class ContextManagerAPI {
	
	public static String NUM_EXECUTIONS="numExecutions";

	public JSONObject getContext(String device, String operation, String user, String property) throws JSONException, IOException{
		String url="https://pedvalar.webs.upv.es/microservices/context/"+device+"/"+operation+"/"+user+"/"+property;
    	JSONObject context=new JSONObject(HTTPClient.get(url));
    	return context;
	}
	
	public void addExecution(String device, String operation, String user) throws JSONException, IOException{
		String url="https://pedvalar.webs.upv.es/microservices/context/"+device+"/"+operation+"/"+user+"/"+NUM_EXECUTIONS;
    	HTTPClient.put(url,"","plain/text",false);
	}
}
