package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import java.util.Hashtable;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;

import es.upv.pros.pvalderas.bpcontroller.execution.ExecutionManager;

public class WoTServiceClass implements JavaDelegate
{
	FixedValue device;
	FixedValue deviceID;
	FixedValue operation;
	FixedValue operationPattern;
	
	
	private ExecutionManager executionManager=ExecutionManager.getCurrentInstance();
    

	@Override
	public void execute(DelegateExecution execution) throws Exception {
    		//https://pedvalar.webs.upv.es/microservices/wot/1805/operation/Turn%20on
    		String deviceIds[]=this.deviceID.getExpressionText().split(";");
    		String deviceNames[]=this.device.getExpressionText().split(";");
    		String operations[]=this.operation.getExpressionText().replaceAll(" ", "%20").split(";");
    		String executionPattern=this.operationPattern.getExpressionText();
    		
    		
    		
    		String user=execution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getUserId();
    		String process=execution.getProcessEngineServices().getRepositoryService().getBpmnModelInstance(execution.getProcessDefinitionId()).getModel().getModelName();
    		String instance=execution.getProcessInstanceId();
    		
    		Hashtable<String,String> actions=new Hashtable<String,String>();
    		switch(executionPattern){
    			case "one": actions=executionManager.executeOneActions(deviceIds, deviceNames, operations, user, process, instance); break;
    			case "oneormore": actions=executionManager.atleastOneActions(deviceIds, deviceNames, operations, user,process, instance); break;
    			case "all": actions=executionManager.executeAllActions(deviceIds, deviceNames, operations, user,process, instance); break;
    		}
    		
    		
    		
        	/*if(results!=null){
        		JSONObject resultJSON=new JSONObject(results);
        		Iterator keys=resultJSON.keys();
        		while(keys.hasNext()){
        			String key=(String)keys.next();
        			execution.setVariable(key, resultJSON.get(key));
        		}
        	}*/
    
    		String actionString="";
    		for(String action:actions.keySet()){
    			actionString+=action+",";
    		}

        	System.out.println("*********************************************************");
    		System.out.println("WoT TASK EXECUTION: "+execution.getCurrentActivityName());
    		System.out.println("Thing Actions: "+actionString.substring(0, actionString.length()-1));
    		System.out.println("*********************************************************");
        	        	
    
    }

}