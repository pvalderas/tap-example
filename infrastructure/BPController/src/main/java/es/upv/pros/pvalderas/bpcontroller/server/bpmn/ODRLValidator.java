package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import java.util.Collection;
import java.util.Iterator;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaField;
import org.json.JSONArray;
import org.json.JSONObject;

import es.upv.pros.pvalderas.http.HTTPClient;
import es.upv.pros.pvalderas.odrl.manager.ODRLManager;

public class ODRLValidator implements ExecutionListener{
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Collection<ServiceTask> serviceTasks=execution.getBpmnModelInstance().getModelElementsByType(ServiceTask.class);
		
		boolean allowed=true;
		Iterator<ServiceTask> it=serviceTasks.iterator();
		
		IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
		String user=identityService.getCurrentAuthentication().getUserId();
		
		JSONObject userActions=new JSONObject();
		userActions.put("user", user);
		JSONArray actions=new JSONArray();
		
		while(allowed && it.hasNext()){
			ServiceTask task=it.next();
			String deviceField="";
			String operationField="";
			if(task.getExtensionElements()!=null){
				Collection<CamundaField> fields=task.getExtensionElements().getChildElementsByType(CamundaField.class);
				
				for(CamundaField field: fields){
					switch(field.getAttributeValue("name")){
						case "deviceID": deviceField=field.getAttributeValue("stringValue").replaceAll(" ", "%20"); break;
						case "operation": operationField=field.getAttributeValue("stringValue").replaceAll(" ", "%20"); break;
					}
				}
				
				
				
				String deviceIds[]=deviceField.split(";");
				String operations[]=operationField.split(";");
				
				for(int i=1;i<deviceIds.length;i++){
					String deviceId=deviceIds[i];
					String operationName=operations[i];
			
					/*String url="https://pedvalar.webs.upv.es/microservices/wot/"+deviceId+"/user/"+user+"/odrl/rules/"+operationName;
			    	JSONObject policies=new JSONObject(HTTPClient.get(url));
			    	
			    	if(!odrl.checkPolicies(policies,deviceId,operationName,user)) allowed=false;*/
					JSONObject action=new JSONObject();
					action.put("deviceId",deviceId);
					action.put("operationName",operationName);
					
					actions.put(action);
				}
			}
		}
		
		if(!allowed){
			System.out.println("NOT ALLOWED");
			execution.getProcessEngineServices().getRuntimeService().deleteProcessInstance(execution.getProcessInstanceId(), "ODRL policies not satisfied");
		}
    	else System.out.println("ALLOWED");
		
		
	}

}
