package airsystem;
import org.json.JSONObject;

public class SetTemperatureResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}