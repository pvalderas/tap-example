package es.upv.pros.pvalderas.policy.manager;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.upv.pros.pvalderas.http.HTTPClient;
import es.upv.pros.pvalderas.policy.manager.odrl.EvaluationResult;
import es.upv.pros.pvalderas.policy.manager.odrl.ODRLManager;

@RestController
public class PolicyManagerHTTPController {
	
	@Autowired
	private ODRLManager odrlManager;

	@RequestMapping(
			  value = "/process/user/policies", 
			  method = RequestMethod.POST,
			  consumes = "application/json")
	public String checkPermissions(@RequestBody String processDesc)  {
		
		boolean allowed=true;
		EvaluationResult result;
		try {
			JSONObject process=new JSONObject(processDesc);
	
			
			JSONArray actions=process.getJSONArray("actions");
			String user=process.getString("user");
			
			for(int i=0;i<actions.length();i++){
				String deviceId=actions.getJSONObject(i).getString("deviceId");
				String operationName=actions.getJSONObject(i).getString("operation");
				String url="https://pedvalar.webs.upv.es/microservices/wot/"+deviceId+"/user/"+user+"/odrl/rules/"+operationName;
		    	JSONObject policies=new JSONObject(HTTPClient.get(url));
			
		    	result=odrlManager.checkPolicies(policies,deviceId, operationName, user);
				if(!result.getResult()) break;
			}
			
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		} 
		
		
		return result.toString();
		
	}
}
