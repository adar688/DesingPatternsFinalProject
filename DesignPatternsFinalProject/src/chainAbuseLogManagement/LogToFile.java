package chainAbuseLogManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogToFile extends LogObserver {
    private String logFilePath;
    
    public LogToFile(String filePath) {
        this.logFilePath = filePath;
    }
    
    /**
    * Updates the log file by appending a new log entry.
    * Uses FileWriter in append mode to preserve existing logs.
    * Each log entry is written on a new line.
    * 
    * @param log The log message to append to the file
    */
    @Override
    public void update(String log) {
        try (FileWriter fw = new FileWriter(logFilePath, true);
             PrintWriter writer = new PrintWriter(fw)) {
            
            writer.println(log);
            
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}