package chainAbuseAPIConn;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Report {

	// TODO: remove Link if still useless when were done
	private String link;
	private String address;
	private int abuseCount;
	
	public Report(String address,HttpResponse<String> report) {
		this.address = address;
		link = "chainabuse.com/address/" + address; // TODO: add configuration
		calcCount(report.body());
	}
	
	/***
	 * Gets a response body and returns the amount of reports in it (properties in the JSON)
	 * @param body
	 * @return count of abuses
	 */
	private void calcCount(String body) {
		int cnt = 0;
		int open = 0;
		for(char c: body.toCharArray()) {
			if(c == '{') {
				open++;
			}
			else if(c == '}') {
				open--;
				if(open == 0) {
					cnt++;
				}
			}
		}
		abuseCount = cnt;
	}

	public String getLink() {
		return link;
	}

	public String getAddress() {
		return address;
	}

	public int getAbuseCount() {
		return abuseCount;
	}
	
//	public static void main(String[] args) {
//		
//		HttpResponse<String> response = null;
//		 try {
//	            HttpRequest request = new ChainAbuseRequestBuilder("https://api.chainabuse.com/v0")
//	                .endpoint("/reports")
//	                .basicAuth("Y2FfV0RnelF6WkxZbEZhYkRaRlRHUlVaVnBWVVZZM1JYWlhMaTlKVWxnd2FuRXZkVVJ2ZG1vM09VOXVkMFZDWjJjOVBROmNhX1dEZ3pRelpMWWxGYWJEWkZUR1JVWlZwVlVWWTNSWFpYTGk5SlVsZ3dhbkV2ZFVSdmRtbzNPVTl1ZDBWQ1oyYzlQUQ==")
//	                .address("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq")
//	                .build();
//	                
//	            response = HttpClient.newHttpClient()
//	                .send(request, HttpResponse.BodyHandlers.ofString());
//	            System.out.println("In the new builder");
//	            //System.out.println(request.toString());  
//	            System.out.println(response.body());
//	            
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//		
//		Report r = new Report("chainabuse.com/report/","bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq",response);
//		System.out.println(r.getAbuseCount());
//		System.out.println(r.getAddress());
//		System.out.println(r.getLink());
//	}
}
