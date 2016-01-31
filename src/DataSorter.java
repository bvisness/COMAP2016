import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class DataSorter {
	public static void main(String[] args) {
		try {
			String baseURL = "https://www.space-track.org";
			String authPath = "/auth/login";
			String userName = "BAD USERNAME";
			String password = "BAD PASSWORD";
			String query = "/basicspacedata/query/class/satcat/OBJECT_TYPE/DEBRIS/orderby/RCS_SIZE asc/metadata/false";
			
			CookieManager manager = new CookieManager();
			manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(manager);
			
			URL url = new URL(baseURL+authPath);
			
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			String input = "identity="+userName+"&password="+password;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output); 
			}

			url = new URL(baseURL + query);

			br = new BufferedReader(new InputStreamReader((url.openStream())));
			PrintWriter out = new PrintWriter(new File("output.txt"));
			while ((output = br.readLine()) != null) {
					out.println(output);
			}
			
			out.close();

			url = new URL(baseURL + "/ajaxauth/logout");
			br = new BufferedReader(new InputStreamReader((url.openStream())));
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
