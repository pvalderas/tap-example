package es.upv.pros.pvalderas.bpcontroller.server.bpmn;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.json.JSONException;
import org.json.JSONObject;

import es.upv.pros.pvalderas.http.HTTPClient;

public class BPMNServiceClass implements JavaDelegate
{
    
    public void execute(final DelegateExecution execution) throws IOException, JSONException, TimeoutException {
    	     
		System.out.println("*********************************************************");
		System.out.println("SERVICE TASK EXECUTION: "+execution.getCurrentActivityName());
		System.out.println("*********************************************************");
    }
}