package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.MessageFlow;
import org.camunda.bpm.model.bpmn.instance.SendTask;
import org.camunda.bpm.model.bpmn.instance.ThrowEvent;

public class MessageSender implements ExecutionListener {
	
	public void notify(DelegateExecution execution) throws Exception {
        
        FlowElement element=execution.getBpmnModelElementInstance();
        MessageEventDefinition msg=null;
        if(element instanceof ThrowEvent){
        	ThrowEvent event=(ThrowEvent)element;
        	msg=(MessageEventDefinition)event.getChildElementsByType(MessageEventDefinition.class).iterator().next();
        }else{ 
        	SendTask sendTask=(SendTask)element;
        	for(MessageFlow flow:execution.getBpmnModelInstance().getModelElementsByType(MessageFlow.class)){
        		if(flow.getSource().getId()==element.getId());
        			msg=(MessageEventDefinition)flow.getTarget().getChildElementsByType(MessageEventDefinition.class).iterator().next();
        	}
        }
       
        if(msg!=null){
	    	System.out.println("SEND MESSAGE: "+msg.getMessage().getName());
	        execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation(msg.getMessage().getName()).correlateWithResult();
        }else{
        	System.out.println("***********************************************************");
 	    	System.out.println("ERROR!! SEND MESSAGE: "+element.getName() +" HAS NOT A MESSAGE TO SEND");
 	        System.out.println("***********************************************************");
        }
        
        /*.processInstanceBusinessKey("someOrderId")
        .setVariable("CANCEL_REASON", "someReason")
        .setVariable("CANCEL_TIMESTAMP", new Date())
        .correlate();*/

    }
	
 }