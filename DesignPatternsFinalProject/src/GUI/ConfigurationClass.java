package GUI;
import java.io.*;
import java.util.Properties;

public class ConfigurationClass {
    private static ConfigurationClass config;
    private Properties properties;
    private final String CONFIG_FILE = "config.properties";
    
    private ConfigurationClass() {
        properties = new Properties();
        loadConfig();
    }
    
    public static ConfigurationClass getConfig() {
        if(config == null) {
            config = new ConfigurationClass();
        }
        return config;
    }
    
    /**
    * Loads configuration properties from a properties file.
    * The file should contain configuration settings like file paths, URLs, and API keys.
    * 
    * If the configuration file cannot be loaded, prints an example configuration
    * structure to help users create their own config file.
    */
    private void loadConfig() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
        	System.out.println("Failed to load configuration, make sure you have a config.properties file with the structure of the following example:");
        	System.out.println("logFilePath=custom_logs.txt\r\n" + 
        			"websiteURL=https://api.chainabuse.com/v0\r\n" + 
        			"endpoint=/reports\r\n" + 
        			"apiKey=your_api_key\r\n" + 
        			"resaultLinkBase=chainabuse.com/address/");
        }
    }
    
	 // Getters for all configurations
	 public String getLogFilePath() {
	     return properties.getProperty("logFilePath");
	 }
	
	 public String getWebsiteURL() {
	     return properties.getProperty("websiteURL");
	 }
	
	 public String getEndpoint() {
	     return properties.getProperty("endpoint");
	 }
	
	 public String getApiKey() {
	     return properties.getProperty("apiKey");
	 }
	
	 public String getResaultLinkBase() {
	     return properties.getProperty("resaultLinkBase");
	 }
}