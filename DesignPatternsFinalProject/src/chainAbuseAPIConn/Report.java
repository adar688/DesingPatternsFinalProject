package chainAbuseAPIConn;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import GUI.ConfigurationClass;

public class Report {

	private final ConfigurationClass config;
	private String link;
	private String address;
	private int abuseCount;
	
	public Report(String address,HttpResponse<String> report) {
		config = ConfigurationClass.getConfig();
		this.address = address;
		link = config.getResaultLinkBase() + address;
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
}