package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDataReader {

	public  Map<String, String> map = new HashMap<String, String>();

	public  Map<String, Integer> testCaseNum = new HashMap<String, Integer>();

	public  void JsonReader(String tcName) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		File srcFile = getFile("UserData");
		File testCaseFile = getFile("testCaseNum");
		
		JSONArray jarray = (JSONArray) parser.parse(new FileReader(srcFile));
		JSONObject jobject = (JSONObject) parser.parse(new FileReader(testCaseFile));
		
		int num = ((Long) jobject.get(tcName)).intValue() ;
		fillData(jarray , num);
	}

	private  File getFile(String fileName) {
		return new File(System.getProperty("user.dir") + "\\src\\test\\java\\data\\" + fileName + ".json");
	}

	private  void fillData(JSONArray jarray , int idx) 
	{	
			JSONObject person =(JSONObject) jarray.get(idx);
			map.put("firstname", (String) person.get("firstname"));
			map.put("lastname", (String) person.get("lastname"));
			map.put("phone", (String) person.get("phone"));
			map.put("email", (String) person.get("email"));
			map.put("password", (String) person.get("password"));
			map.put("confirm_password", (String) person.get("confirm_password"));
		
	}

}


