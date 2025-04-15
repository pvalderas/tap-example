package es.upv.pros.pvalderas.bpcontroller.execution;

import java.io.IOException;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import es.upv.pros.pvalderas.context.manager.ContextManager;
import es.upv.pros.pvalderas.http.HTTPClient;
import es.upv.pros.pvalderas.rabbitmq.RabbitMQManager;


public class ExecutionManager {
	
	public static ExecutionManager getCurrentInstance(){
		if(instance==null) instance=new ExecutionManager();
		return instance;
	}
	
	private static ExecutionManager instance;
	private ExecutionManager(){}
	
	//private ContextManager contextManager=ContextManager.getCurrentInstance();
	private RabbitMQManager rabbit=RabbitMQManager.getCurrentInstance();
	
	
	public Hashtable<String,String> executeOneActions(String[] deviceIds, String[] deviceNames, String[] operations, String user, String process, String instance) throws IOException, JSONException{
		Hashtable<String,String> actions=new Hashtable<String,String>();
	
		String deviceId=deviceIds[1];
		String deviceName=deviceNames[1];
		String operation=operations[1];
		
		String results=executeAction(deviceId, operation, user, process, instance);
		actions.put(deviceName+"."+operation.replaceAll("%20"," "),results);
		
		return actions;
	}
	
	public Hashtable<String,String> atleastOneActions(String[] deviceIds, String[] deviceNames, String[] operations, String user, String process, String instance) throws IOException, JSONException{
		Hashtable<String,String> actions=new Hashtable<String,String>();
		
		for(int i=1;i<deviceIds.length;i++){
			String deviceId=deviceIds[i];
			String deviceName=deviceNames[i];
			String operation=operations[i];
			
			String results=executeAction(deviceId, operation, user, process, instance);
			actions.put(deviceName+"."+operation.replaceAll("%20"," "),results);
		}
		
		return actions;
	}
	
	
	public Hashtable<String,String> executeAllActions(String[] deviceIds, String[] deviceNames, String[] operations, String user, String process, String instance) throws IOException, JSONException{
		Hashtable<String,String> actions=new Hashtable<String,String>();
		
		for(int i=1;i<deviceIds.length;i++){
			String deviceId=deviceIds[i];
			String deviceName=deviceNames[i];
			String operation=operations[i];
			
			String results=executeAction(deviceId, operation, user, process, instance);
			actions.put(deviceName+"."+operation.replaceAll("%20"," "),results);
		}
		
		return actions;
	}
	
	private String executeAction(String deviceId, String operation, String user, String process, String instance) throws JSONException, IOException{
		String response=HTTPClient.get("https://pedvalar.webs.upv.es/microservices/wot/"+deviceId+"/operation/"+operation);
		 
    	JSONObject operationData=new JSONObject(response);
    	String protocol=operationData.getString("protocol").toUpperCase();
    	String results=null;
    	switch(protocol){
    		case "HTTPS":
    		case "HTTP":  results=this.executeHTTP(operationData);break;
    		case "MQTT":  this.executeMQTT(operationData);break;
    	}
    	
    	JSONObject execution=new JSONObject();
    	execution.put("deviceId", deviceId);
    	execution.put("operation", operation);
    	execution.put("user", user);
    	execution.put("process", process);
    	execution.put("instance", instance);
    	
    	rabbit.publishMessage(execution.toString());
    	//contextManager.addExecution(deviceId, operation, user);
    	return results;
	}
	
	private String executeHTTP(JSONObject operationData) throws JSONException, IOException{

    	String port="";
    	if(operationData.getInt("port")!=0 && operationData.getInt("port")!=80) port=":"+String.valueOf(operationData.getInt("port"));
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
    
    public void executeMQTT(JSONObject operationData){
    
    }


}
