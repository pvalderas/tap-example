package accesscontroller.results;
import org.json.JSONObject;

public class AllowAccessResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}