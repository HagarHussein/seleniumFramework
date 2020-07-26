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

	public Map<String, String> map = new HashMap<String, String>();

	public void JsonReader() throws FileNotFoundException, IOException, ParseException {
		File srcFile = getFile();

		JSONParser parser = new JSONParser();
		JSONArray jarray = (JSONArray) parser.parse(new FileReader(srcFile));

		fillData(jarray);
	}

	private File getFile() {
		return new File(System.getProperty("user.dir") + "\\src\\test\\java\\data\\UserData.json");
	}

	private void fillData(JSONArray jarray) {
		for (Object jsonObj : jarray) {
			JSONObject person = (JSONObject) jsonObj;
			map.put("firstname", (String) person.get("firstname"));
			map.put("lastname", (String) person.get("lastname"));
			map.put("phone", (String) person.get("phone"));
			map.put("email", (String) person.get("email"));
			map.put("password", (String) person.get("password"));
		}
	}

}
