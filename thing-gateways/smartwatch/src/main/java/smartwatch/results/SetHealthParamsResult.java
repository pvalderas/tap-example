package smartwatch.results;
import org.json.JSONObject;

public class SetHealthParamsResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}