package airsystem;
import org.json.JSONObject;

public class GetTemperatureResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}