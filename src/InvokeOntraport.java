import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class InvokeOntraport {
	public InvokeOntraport() {
	}

	public static void main(String[] a) throws MalformedURLException, IOException {

		
		/*try {
			DefaultHttpClient client = null;
			HttpPost httpPost = null;
			Map<String, Object> inputMap = new HashMap();
			inputMap.put("FirstName", "Sudharshana");
			inputMap.put("LastName", "R");
			inputMap.put("Email", "sudharshana_r@soalrtis.com");

			List<String> inputList = new ArrayList();
			inputList.add("Store1");
			inputList.add("Store2");

			inputMap.put("ListOfStores", inputList);

			System.out.println("inputMap " + inputMap);

			JSONObject inputJSONobject = new JSONObject(inputMap);
			String inputJSON = inputJSONobject.toString();

			System.out.println(inputJSONobject);

			client = new DefaultHttpClient();
			httpPost = new HttpPost("https://api.ontraport.com/1/objects/saveorupdate");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Api-Appid", "2_194428_28h7HIeny");
			httpPost.setHeader("Api-Key", "MaLTjcMRFteWbF8");
			StringEntity params = new StringEntity(inputJSON);

			httpPost.setEntity(params);
			HttpResponse httpResponse = client.execute(httpPost);

			System.out.println("Response Status: " + httpResponse.getStatusLine());
			System.out.println("Response: " + httpResponse);
		} catch (Exception e) {
			System.out.println("Error!!");
			e.printStackTrace();
		}*/
		
		invoke();
	}

public static void invoke() throws MalformedURLException, IOException {
	
	HttpURLConnection urlConnection = null;
	InputStream inputStream = null;
	OutputStream os = null;
	int httpResponseCode = 0;
	String responseString = "";
	Map<String, Object> inputMap = new HashMap();
	inputMap.put("FirstName", "Sudharshana");
	inputMap.put("LastName", "R");
	inputMap.put("Email", "sudharshana_r@soalrtis.com");

	List<String> inputList = new ArrayList();
	inputList.add("Store1");
	inputList.add("Store2");

	inputMap.put("ListOfStores", inputList);

	System.out.println("inputMap " + inputMap);

	JSONObject inputJSONobject = new JSONObject(inputMap);
	String inputJSON = inputJSONobject.toString();

	System.out.println(inputJSONobject);
	
	
	urlConnection = (HttpURLConnection) new URL("https://api.ontraport.com/1/objects/saveorupdate").openConnection();
	urlConnection.setDoOutput(true);
	urlConnection.setDoInput(true);
	urlConnection.setRequestProperty("Content-Type", "application/json");
	urlConnection.setRequestProperty("Api-Appid", "2_194428_28h7HIeny");
	urlConnection.setRequestProperty("Api-Key", "MaLTjcMRFteWbF8");
	
	os = urlConnection.getOutputStream();
	os.write(inputJSONobject.toString().getBytes("UTF-8"));
	os.flush();
	os.close();
	
	httpResponseCode = urlConnection.getResponseCode();
	inputStream = new BufferedInputStream(urlConnection.getInputStream());
	responseString = getStringFromInputStream(inputStream);
	System.out.println(responseString);
	inputStream.close();
}
private static String getStringFromInputStream(InputStream inputStream) throws IOException {
	StringBuilder stringBuilder = new StringBuilder();

	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	String line;
	while ((line = bufferedReader.readLine()) != null) {
		stringBuilder.append(line);
	}
	if (bufferedReader != null) {
		bufferedReader.close();
	}
	return stringBuilder.toString();
}
}