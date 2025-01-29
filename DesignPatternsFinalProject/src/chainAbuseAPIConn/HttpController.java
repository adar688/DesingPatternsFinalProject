package chainAbuseAPIConn;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpController {

	private ChainAbuseRequestBuilder requestBuilder;
	private List<String> addresses;
	private final HttpClient httpClient;

	public HttpController(String baseUrl, String endpoint, String apiKey, List<String> addresses) {
		httpClient = HttpClient.newHttpClient();
		this.addresses = addresses;
		// Make basic builder to run
		requestBuilder = new ChainAbuseRequestBuilder(baseUrl)
				.endpoint(endpoint)
				.basicAuth(apiKey);
	}
	
	public Map<String, HttpResponse<String>> fetchAllResponses() throws Exception {
        Map<String, HttpResponse<String>> responses = new HashMap<>();
        
        for (String address : addresses) {
            HttpRequest request = requestBuilder
                    .address(address)
                    .build();
                    
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    
            responses.put(address, response);
        }
        
        return responses;
    }
	
	
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<>();
		arr.add("19TA5Sq3RP4JV2sn3UG1TNW4hRLjYKLyFx");
		arr.add("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq");
		arr.add("bc1qcrx80eklp9tdz4sx7p0v3ghvc2w5v2h9seqgln");
		arr.add("3NmbqMoydJUDaUTkkyf3yt4EovrZV81mW1");
		arr.add("1Agp6hLhVhEwZ9px4LZrqN2j8PoW9vPUGR");
		HttpController hc = new HttpController("https://api.chainabuse.com/v0","/reports","Y2FfTmpSdmVUazRlR3hRUkZKdGRWZzNXVGRQWjFWV1dHeHVMa2xFVHpablJVSkllRGQ0TVRkUkwxcHlSVEpuTlVFOVBROmNhX05qUnZlVGs0ZUd4UVJGSnRkVmczV1RkUFoxVldXR3h1TGtsRVR6Wm5SVUpJZURkNE1UZFJMMXB5UlRKbk5VRTlQUQ==",arr);
		try {
			Map<String, HttpResponse<String>> responses = hc.fetchAllResponses();
			for(String address: arr) {
				System.out.println(address + responses.get(address).body());
			}
		} catch(Exception e) {
			System.out.println(String.format("Error while getting responses or somthin", e)); // TODO: fix message
		}
		
	}
}
