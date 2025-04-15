package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaField;

import es.upv.pros.pvalderas.http.HTTPClient;

public class ConditionEvaluator implements ExecutionListener {
	
	public boolean validate(){
			return true;
	}
	
	public void notify(DelegateExecution execution) throws Exception {
	  
		/*YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ClassPathResource("application.yml"));
        Properties props=yamlFactory.getObject();
        String contextMonitorURL=props.getProperty("contextMonitor.serviceUrl");
        String conditionPath=props.getProperty("contextMonitor.conditionPath");
        
        JSONObject condition=new JSONObject();
        condition.put("data", execution.getVariable("results"));
        condition.put("query", execution.getCurrentActivityName());
        
        String results=HTTPClient.post(contextMonitorURL+conditionPath, condition.toString(), true, "application/json");
		
		execution.setVariable("conditionResult", Boolean.parseBoolean(results));*/
		String contextMonitorURL="http://pedvalar.webs.upv.es/microservices/condition/result";
		String condition=execution.getCurrentActivityName();
		if(condition==null || condition.length()==0){
			for(CamundaField field:execution.getBpmnModelElementInstance().getExtensionElements().getChildElementsByType(CamundaField.class)){
				if(field.getAttributeValue("name").equals("conditionName")) condition=field.getAttributeValue("stringValue");
			}	
		}
		condition=new String (condition);
		String results=HTTPClient.post(contextMonitorURL, condition, "text/plain", true);
		
		execution.setVariable("conditionResult", results);

    	System.out.println("CONDITION EVALUATION: "+condition+" -->"+results);

    }
	
 }