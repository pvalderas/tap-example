package es.upv.pros.pvalderas.policy.manager.odrl;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.upv.pros.pvalderas.policy.context.ContextManager;

	
@Component
public class ODRLManager {
	
	@Autowired
	private ContextManager contextManager;
	
	public EvaluationResult checkPolicies(JSONObject policy, String device, String operation, String user) throws JSONException, IOException{
			EvaluationResult evaluationResult=new EvaluationResult();
			evaluationResult.setResult(true);
			
			if(policy.getString("type").equals("permission")){
				boolean result=true;
	    		if(policy.has("refinement")){
	    			JSONArray refinements=policy.getJSONArray("refinement");
	    			for(int i=0;i<refinements.length();i++)
	    				result=result & manageCondition(refinements.getJSONObject(i), device, operation, user).getResult();
	    		}
	    		if(policy.has("constraint")){
	    			JSONArray constraints=policy.getJSONArray("constraint");
	    			for(int i=0;i<constraints.length();i++)
	    				result=result & manageCondition(constraints.getJSONObject(i), device, operation, user).getResult();
	    		}
	    		if(!result){
	    			evaluationResult.setResult(false);
	    			evaluationResult.setText("Some policy constraint or refinement is not satisfied to allow the user "+user+" to execute the "+operation+" operation");
	    		}
	    	}else{
	    		evaluationResult.setResult(false);
				evaluationResult.setText("User "+user+" has no permission defined for executing the "+operation+" operation");
	    	}
	
	    	//return false;
	    	return evaluationResult;
    }
    
    private EvaluationResult manageCondition(JSONObject condition,String device, String operation, String user) throws JSONException, IOException{
    	switch(condition.getString("leftOperand")){
			case "count": return checkCount(condition,device, operation, user);
		}
    	return null;
    }
    
    private EvaluationResult checkCount(JSONObject condition, String device, String operation, String user) throws JSONException, IOException{
    	EvaluationResult result=new EvaluationResult();
    	boolean valid=true;
    	String text="";
    	
    	JSONObject context=contextManager.getContext(device, operation, user, ContextManager.NUM_EXECUTIONS);
    	
    	int currentExecutions=context.getInt("executions");
    	int policyExecutions=0;
    	try{
    		policyExecutions=condition.getInt("rightOperand");
    	}catch(Exception e){
    		policyExecutions=Integer.parseInt(condition.getString("rightOperand"));
    	}
    	
    	//TODO: "hasPart": "isA": "isAllOf": "isAnyOf": "isNoneOf": "isPartOf": 
    	switch(condition.getString("operator")){
    		case "eq": valid=currentExecutions==policyExecutions;
    		case "gt": valid=currentExecutions>policyExecutions;
    		case "gteq": valid=currentExecutions>=policyExecutions;
    		case "lt": valid=currentExecutions<policyExecutions;
    		case "lteq": valid=currentExecutions<=policyExecutions;
    		case "neq":valid=currentExecutions!=policyExecutions;
    	}

    	result.setResult(valid);
    	if(!valid) text="Execution count of "+operation+" operation is not satified for "+user+" user";
    	result.setText(text);
    	return result;
    }
}
