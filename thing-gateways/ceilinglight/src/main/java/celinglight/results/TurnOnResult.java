package celinglight.results;
import org.json.JSONObject;

public class TurnOnResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}