package videocamera;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import videocamera.results.*;

@RestController
public class VideoCameraActuator {

	@RequestMapping(value="/turn/on",
					method=RequestMethod.GET,
					produces="application/json")
	public String videoon(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/turn/on").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation VideoOn Executed\"}";
		System.out.println("Operation VideoOn Executed");
		return new VideoOnResult().parseResult(result);
	}

	@RequestMapping(value="/turn/off",
					method=RequestMethod.GET,
					produces="application/json")
	public String videooff(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/turn/off").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation VideoOff Executed\"}";
		System.out.println("Operation VideoOff Executed");
		return new VideoOffResult().parseResult(result);
	}

	@RequestMapping(value="/audio/off",
					method=RequestMethod.GET,
					produces="application/json")
	public String audioon(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/audio/off").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Audioon Executed\"}";
		System.out.println("Operation Audioon Executed");
		return new AudioonResult().parseResult(result);
	}

	@RequestMapping(value="/audio/mute",
					method=RequestMethod.GET,
					produces="application/json")
	public String mute(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/audio/mute").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation Mute Executed\"}";
		System.out.println("Operation Mute Executed");
		return new MuteResult().parseResult(result);
	}

	@RequestMapping(value="/video/status",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String setvideostatus(String input){
		String result ="";
/*
		result=restClient.post().uri("https://videocamera.example.com/video/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
*/
		result="{\"message\":\"Operation SetVideoStatus Executed\"}";
		System.out.println("Operation SetVideoStatus Executed");
		return new SetVideoStatusResult().parseResult(result);
	}

	@RequestMapping(value="/video/status",
					method=RequestMethod.GET,
					produces="application/json")
	public String getvideostatus(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/video/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetVideoStatus Executed\"}";
		System.out.println("Operation GetVideoStatus Executed");
		return new GetVideoStatusResult().parseResult(result);
	}

	@RequestMapping(value="/audio/status",
					method=RequestMethod.POST,
					produces="application/json",
					consumes="application/json")
	public String setaudiostatus(String input){
		String result ="";
/*
		result=restClient.post().uri("https://videocamera.example.com/audio/status").contentType(MediaType.APPLICATION_JSON).body(input).retrieve().toBodilessEntity();
*/
		result="{\"message\":\"Operation SetAudioStatus Executed\"}";
		System.out.println("Operation SetAudioStatus Executed");
		return new SetAudioStatusResult().parseResult(result);
	}

	@RequestMapping(value="/audio/status",
					method=RequestMethod.GET,
					produces="application/json")
	public String getaudiostatus(){
		String result ="";
/*
		result=restClient.get().uri("https://videocamera.example.com/audio/status").retrieve().body(String.class);
*/
		result="{\"message\":\"Operation GetAudioStatus Executed\"}";
		System.out.println("Operation GetAudioStatus Executed");
		return new GetAudioStatusResult().parseResult(result);
	}

}