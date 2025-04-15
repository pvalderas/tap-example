package videocamera.results;
import org.json.JSONObject;

public class GetAudioStatusResult {

	public String parseResult(String result){
		JSONObject resultJSON=new JSONObject(result);
		return resultJSON.toString();
	}
}