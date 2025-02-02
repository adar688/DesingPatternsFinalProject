package chainAbuseAPIConn;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.ConfigurationClass;

public class HttpController {

	private ChainAbuseRequestBuilder requestBuilder;
	private final HttpClient httpClient;
	private static HttpController instance;
	private final ConfigurationClass config;

	private HttpController() {
		config = ConfigurationClass.getConfig(); 
		httpClient = HttpClient.newHttpClient();
		// Make basic builder to run
		requestBuilder = new ChainAbuseRequestBuilder(config.getWebsiteURL())
				.endpoint(config.getEndpoint())
				.basicAuth(config.getApiKey());
	}
	
	public static HttpController getInstance() {
		if(instance == null) {
			return new HttpController();
		}
		return instance;
	}
	
	public List<Report> fetchAllReports(List<String> addresses) throws Exception {
        List<Report> reports = new ArrayList<>();
        
        for (String address : addresses) {
            HttpRequest request = requestBuilder
                    .address(address)
                    .build();
                    
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    
            reports.add(new Report(address,response));
        }
        
        return reports;
    }
}