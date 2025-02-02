package GUI;

public class ConfigurationClass {
	
	private static ConfigurationClass config;
	private String logFilePath;
	private String websiteURL;
	private String endpoint;
	private String apiKey;
	
	private String resaultLinkBase;
	
	private ConfigurationClass() {
		// default configuration
		logFilePath = "logs.txt";
		websiteURL = "https://api.chainabuse.com/v0";
		endpoint = "/reports";
		apiKey = "Y2FfTmpSdmVUazRlR3hRUkZKdGRWZzNXVGRQWjFWV1dHeHVMa2xFVHpablJVSkllRGQ0TVRkUkwxcHlSVEpuTlVFOVBROmNhX05qUnZlVGs0ZUd4UVJGSnRkVmczV1RkUFoxVldXR3h1TGtsRVR6Wm5SVUpJZURkNE1UZFJMMXB5UlRKbk5VRTlQUQ==";
		resaultLinkBase = "chainabuse.com/address/";
	}
	
	public String getResaultLinkBase() {
		return resaultLinkBase;
	}

	public void setResaultLinkBase(String resaultLinkBase) {
		this.resaultLinkBase = resaultLinkBase;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public static ConfigurationClass getConfig() {
		if(config == null) {
			config = new ConfigurationClass();
		}
		return config;
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}
}
