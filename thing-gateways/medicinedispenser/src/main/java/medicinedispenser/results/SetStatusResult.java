package medicinedispenser.results;
import org.json.JSONObject;

public class SetStatusResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}