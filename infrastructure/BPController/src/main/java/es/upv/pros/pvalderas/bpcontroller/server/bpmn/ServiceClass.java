package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.json.JSONException;
import org.json.JSONObject;

import es.upv.pros.pvalderas.http.HTTPClient;

public class ServiceClass implements JavaDelegate
{
	FixedValue system;
	FixedValue psm;
	FixedValue microservice;
	FixedValue operation;
	
	FixedValue url;
	FixedValue method;
	
	
	public void execute(){
		System.out.println("TASK EXECUTED");
	}
    
    public void execute(final DelegateExecution execution) throws IOException, JSONException, TimeoutException {
    	     
      /*  String data=execution.getVariable("results")!=null?(String)execution.getVariable("results"):"";
        String results="";
        
        if(url!=null){
    		switch(method.getExpressionText()){
    			case "GET": results = HTTPClient.get(url.getExpressionText());break;
    			case "POST": results = HTTPClient.post(url.getExpressionText(), data, true, "application/json");break;
    		}
        }
				
		execution.setVariable("results", results);
		
		JSONObject resultsJSON=new JSONObject(results);*/

    	String results=null;
        if(psm!=null){
        	String response=HTTPClient.get("http://pedvalar.webs.upv.es/psmServer/system/"+system.getExpressionText().replaceAll(" ", "%20")+"/psm/"+psm.getExpressionText().replaceAll(" ", "%20")+"/device/"+microservice.getExpressionText().replaceAll(" ", "%20")+"/operation/"+operation.getExpressionText().replaceAll(" ", "%20"));
        	JSONObject operationData=new JSONObject(response);
        	String protocol=operationData.getString("protocol");
        	switch(protocol){
        		case "HTTP":  results=executeHTTP(operationData);break;
        		case "MQTT":  executeMQTT(operationData);break;
        	}
        }
        
        execution.setVariable("results", results);
    	System.out.println("REST TASK EXECUTION: "+execution.getCurrentActivityName()+" -->"+results);//+": "+microservice.getExpressionText()+"."+operationPath.getExpressionText());
        
    }
    
    private String executeHTTP(JSONObject operationData) throws JSONException, IOException{

    	String port=operationData.getInt("port")!=0?":"+operationData.getInt("port"):"";
		String path=operationData.getString("path").indexOf("/")==0?operationData.getString("path"):"/"+operationData.getString("path");
    	String response=null;
		switch(operationData.getString("method")){
    		case "GET": 
    			response=HTTPClient.get("http://"+operationData.getString("host")+port+path);
    			break;
    		case "POST":
    			response=HTTPClient.post("http://"+operationData.getString("host")+port+path,"","text/plain", true);
    			break;
    		case "PUT":
    			response=HTTPClient.put("http://"+operationData.getString("host")+port+path,"","text/plain", true);
    			break;
    		case "DELETE":
    			response=HTTPClient.delete("http://"+operationData.getString("host")+port+path);
    			break;
    	}
    	return response;
    }
    
    private void executeMQTT(JSONObject operationData){
    	
    }
}