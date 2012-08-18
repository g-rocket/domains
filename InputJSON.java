import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
public class InputJSON{
	private String data;
	private BufferedReader file;
	private Domain[] domains = null;
	private Camper[] campers = null;
	public InputJSON(String filepath){
		try{
			file = new BufferedReader(new FileReader(filepath));
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public void input(){
		JSONObject jobj;
		try{
			jobj = (JSONObject)(new JSONParser().parse(file));
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try{
				file.close();
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		JSONArray jsonDomains = (JSONArray)jobj.get("domains");
		JSONArray jsonCampers = (JSONArray)jobj.get("campers");
		campers = new Camper[jsonCampers.size()];
		domains = new Domain[jsonDomains.size()];
		for(int i = 0; i < campers.length; i++){
			JSONObject camper = (JSONObject)jsonCampers.get(i);
			String name = (String)camper.get("name");
			JSONArray prefs = (JSONArray)camper.get("preferences");
			String[] prefrences = new String[prefs.size()];
			for(int j = 0; j < prefs.size(); j++){
				prefrences[j] = (String)prefs.get(j);
			}
			campers[i] = new Camper(name, domains.length, prefrences);
		}
		for(int i = 0; i < domains.length; i++){
			JSONObject domain = (JSONObject)jsonDomains.get(i);
			String name = (String)domain.get("name");
			int capacity = ((Number)domain.get("capacity")).intValue();
			domains[i] = new Domain(name, capacity);
		}
	}
	public Domain[] getDomains(){
		if(domains == null) throw new IllegalStateException("not inputed");
		return domains;
	}
	public Camper[] getCampers(){
		if(campers == null) throw new IllegalStateException("not inputed");
		return campers;
	}
}