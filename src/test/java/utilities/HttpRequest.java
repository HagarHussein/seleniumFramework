
package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class HttpRequest {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String POST_URL = "https://www.phptravels.net/register";

	public static void sendPOST(String firstname, String lastname, String phone, String email, String password)
			throws IOException {
		URL obj = new URL(POST_URL);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		// Read data from the same JSON file (test data)
		os.write(firstname.getBytes());
		os.write(lastname.getBytes());
		os.write(phone.getBytes());
		os.write(email.getBytes());
		os.write(password.getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			saveResponse(response);
		} else {
			System.out.println("POST request not worked");
		}
	}

	private static void saveResponse(StringBuffer response) throws IOException {
		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("./Responses/response.html")));

			// write contents of StringBuffer to a file
			bwr.write(response.toString());

			// flush the stream
			bwr.flush();

			// close the stream
			bwr.close();
			System.out.println("Response is saved successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
