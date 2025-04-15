package celinglight.results;
import org.json.JSONObject;

public class TurnOffResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}