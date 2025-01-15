package chainAbuseAPIConn;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ChainAbuseRequestBuilder {
    private final HttpRequest.Builder requestBuilder;
    private final String baseUrl;
    private final Map<String, String> queryParams;
    public String endpoint;
    
    public ChainAbuseRequestBuilder(String baseUrl) {
        this.requestBuilder = HttpRequest.newBuilder();
        this.baseUrl = baseUrl;
        this.queryParams = new HashMap<>();
        
        // Set default parameters
        requestBuilder.header("accept", "application/json");
        queryParams.put("includePrivate", "false");
        queryParams.put("page", "1");
        queryParams.put("perPage", "50");
        endpoint = "/reports";
    }
    
    /***
     * Changes the endpoint to the url
     * @param endpoint
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder endpoint(String endpoint) {
    	this.endpoint = endpoint;
        return this;
    }
    
    /***
     * Adds a custom header for the Http Request
     * @param key
     * @param value
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder header(String key, String value) {
        requestBuilder.header(key, value);
        return this;
    }
    
    /***
     * Add api key to the request
     * @param apiKey
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder basicAuth(String apiKey) {
        return header("authorization", "Basic " + apiKey);
    }
    
    /***
     * Add crypto address to the request
     * @param address
     * @return
     */
    public ChainAbuseRequestBuilder address(String address) {
        return queryParam("address", address);
    }
    
    /***
     * Add a custom parameter to the request
     * @param key
     * @param value
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder queryParam(String key, String value) {
        queryParams.put(key, value);
        return this;
    }
    
    // Optional methods to override defaults
    
    /***
     * Customize inclusion of private reports
     * @param include
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder includePrivate(boolean include) {
        return queryParam("includePrivate", String.valueOf(include));
    }
    
    /***
     * Customize which page will be read
     * @param page
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder page(int page) {
        return queryParam("page", String.valueOf(page));
    }
    
    /***
     * Customize how many reports will be read
     * @param perPage
     * @return current ChainAbuseRequestBuilder object
     */
    public ChainAbuseRequestBuilder perPage(int perPage) {
        return queryParam("perPage", String.valueOf(perPage));
    }
    
    /***
     * Builds and returns an HttpRequest
     * @return new HttpRequest
     */
    public HttpRequest build() {
    	
    	StringBuilder urlBuilder = new StringBuilder(baseUrl);
    	
    	// Add endpoint to base url
    	if (endpoint != null && !endpoint.isEmpty()) {
            if (!endpoint.startsWith("/")) {
                urlBuilder.append("/");
            }
            urlBuilder.append(endpoint);
        }
    	
        // Add query parameters to URL
        if (!queryParams.isEmpty()) {
            urlBuilder.append("?");
            queryParams.forEach((key, value) -> {
                urlBuilder.append(key).append("=").append(value).append("&");
            });
            // Remove the last "&"
            urlBuilder.setLength(urlBuilder.length() - 1);
        }
        
        // Set the final URI and GET method and return request
        return requestBuilder.uri(URI.create(urlBuilder.toString()))
                     .GET()
                     .build();
    }
    
    public static void main(String[] args) {
        try {
            HttpRequest request = new ChainAbuseRequestBuilder("https://api.chainabuse.com/v0")
                .endpoint("/reports")
                .basicAuth("Y2FfZG5aRVVYVmxOREJWUzFGRWFYRldXVWxyVVVaNGFuVTFMa1Z1T1ZsSFRWUmhNREJJZDBkeWVVMWhRbTFEVUdjOVBROmNhX2RuWkVVWFZsTkRCVlMxRkVhWEZXV1VsclVVWjRhblUxTGtWdU9WbEhUVlJoTURCSWQwZHllVTFoUW0xRFVHYzlQUQ==")
                .address("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq")
                .build();
                
            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("In the new builder");
            //System.out.println(request.toString());  
            System.out.println(response.body());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}