package lamp.results;
import org.json.JSONObject;

public class GetStatusResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}