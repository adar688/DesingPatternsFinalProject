package chainAbuseLogManagement;
import javafx.scene.control.TextArea;
import javafx.application.Platform;

public class LogToUI extends LogObserver {
    private TextArea logArea;
    
    public LogToUI(TextArea logArea) {
        this.logArea = logArea;
    }
    
    /**
    * Updates the UI TextArea with a new log entry.
    * Uses Platform.runLater to ensure thread-safe UI updates.
    * Each log entry is appended on a new line.
    * 
    * @param log The log message to be displayed in the TextArea
    */
    @Override
    public void update(String log) {
        Platform.runLater(() -> {
            logArea.appendText(log + "\n");
        });
    }
}