package es.upv.pros.pvalderas.policy.manager.odrl;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResult {
	private boolean result;
	private List<String> text=new ArrayList<String>();
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public List<String> getText() {
		return text;
	}
	public void addText(String text) {
		this.text.add(text);
	}

}
