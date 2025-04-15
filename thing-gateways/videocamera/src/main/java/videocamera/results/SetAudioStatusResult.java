package videocamera.results;
import org.json.JSONObject;

public class SetAudioStatusResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}