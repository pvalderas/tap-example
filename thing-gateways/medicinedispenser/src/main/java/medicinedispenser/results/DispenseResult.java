package medicinedispenser.results;
import org.json.JSONObject;

public class DispenseResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}