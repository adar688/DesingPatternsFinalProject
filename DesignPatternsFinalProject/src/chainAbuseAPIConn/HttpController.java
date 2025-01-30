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
	private final HttpClient httpClient;
	private static HttpController instance;

	private HttpController(String baseUrl, String endpoint, String apiKey) {
		httpClient = HttpClient.newHttpClient();
		// Make basic builder to run
		requestBuilder = new ChainAbuseRequestBuilder(baseUrl)
				.endpoint(endpoint)
				.basicAuth(apiKey);
	}
	
	public static HttpController getInstance() {
		if(instance == null) {
			// TODO: make this configurable
			return new HttpController("https://api.chainabuse.com/v0","/reports","Y2FfTmpSdmVUazRlR3hRUkZKdGRWZzNXVGRQWjFWV1dHeHVMa2xFVHpablJVSkllRGQ0TVRkUkwxcHlSVEpuTlVFOVBROmNhX05qUnZlVGs0ZUd4UVJGSnRkVmczV1RkUFoxVldXR3h1TGtsRVR6Wm5SVUpJZURkNE1UZFJMMXB5UlRKbk5VRTlQUQ==");
		}
		return instance;
	}
	
	public List<Report> fetchAllResports(List<String> addresses) throws Exception {
        List<Report> reports = new ArrayList<>();
        
        for (String address : addresses) {
            HttpRequest request = requestBuilder
                    .address(address)
                    .build();
                    
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    
            reports.add(new Report("chainabuse.com/report/",address,response));
        }
        
        return reports;
    }
	
	
//	public static void main(String[] args) {
//		ArrayList<String> arr = new ArrayList<>();
//		arr.add("19TA5Sq3RP4JV2sn3UG1TNW4hRLjYKLyFx");
//		arr.add("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq");
//		arr.add("bc1qcrx80eklp9tdz4sx7p0v3ghvc2w5v2h9seqgln");
//		arr.add("3NmbqMoydJUDaUTkkyf3yt4EovrZV81mW1");
//		arr.add("1Agp6hLhVhEwZ9px4LZrqN2j8PoW9vPUGR");
//		HttpController hc = HttpController.getInstance();
//		try {
//			Map<String, HttpResponse<String>> responses = hc.fetchAllResponses(arr);
//			for(String address: arr) {
//				System.out.println(address + responses.get(address).body());
//			}
//		} catch(Exception e) {
//			System.out.println(String.format("Error while getting responses or somthin", e)); // TODO: fix message
//		}
//		
//	}
}
