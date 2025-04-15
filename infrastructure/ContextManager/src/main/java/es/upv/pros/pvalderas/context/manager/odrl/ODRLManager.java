package es.upv.pros.pvalderas.context.manager.odrl;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.upv.pros.pvalderas.context.manager.ContextManagerAPI;


@Component
public class ODRLManager {
	
	@Autowired
	private ContextManagerAPI contextManager;
	
	public boolean checkPolicies(JSONObject policy, String device, String operation, String user) throws JSONException, IOException{
	    	if(policy.getString("type").equals("permission")){
	    		boolean result=true;
	    		if(policy.has("refinement")){
	    			JSONArray refinements=policy.getJSONArray("refinement");
	    			for(int i=0;i<refinements.length();i++)
	    				result=result & manageCondition(refinements.getJSONObject(i), device, operation, user);
	    		}
	    		if(policy.has("constraint")){
	    			JSONArray constraints=policy.getJSONArray("constraint");
	    			for(int i=0;i<constraints.length();i++)
	    				result=result & manageCondition(constraints.getJSONObject(i), device, operation, user);
	    		}
	    		return result;
	    	} 
	
	    	//return false;
	    	return true;
    }
    
    private boolean manageCondition(JSONObject condition,String device, String operation, String user) throws JSONException, IOException{
    	switch(condition.getString("leftOperand")){
			case "count": return checkCount(condition,device, operation, user);
		}
    	return true;
    }
    
    private boolean checkCount(JSONObject condition, String device, String operation, String user) throws JSONException, IOException{
    		
    	JSONObject context=contextManager.getContext(device, operation, user, ContextManagerAPI.NUM_EXECUTIONS);
    	
    	int currentExecutions=context.getInt("executions");
    	int policyExecutions=0;
    	try{
    		policyExecutions=condition.getInt("rightOperand");
    	}catch(Exception e){
    		policyExecutions=Integer.parseInt(condition.getString("rightOperand"));
    	}
    	
    	//TODO: "hasPart": "isA": "isAllOf": "isAnyOf": "isNoneOf": "isPartOf": 
    	switch(condition.getString("operator")){
    		case "eq": return currentExecutions==policyExecutions;
    		case "gt": return currentExecutions>policyExecutions;
    		case "gteq": return currentExecutions>=policyExecutions;
    		case "lt": return currentExecutions<policyExecutions;
    		case "lteq": return currentExecutions<=policyExecutions;
    		case "neq":return currentExecutions!=policyExecutions;
    	}

    	return true;
    }
}
