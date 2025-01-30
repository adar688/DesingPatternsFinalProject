package chainAbuseLogManagement;
import javafx.scene.control.TextArea;
import javafx.application.Platform;

public class LogToUI extends LogObserver {
    private TextArea logArea;
    
    public LogToUI(TextArea logArea) {
        this.logArea = logArea;
    }
    
    @Override
    public void update(String log) {
        // Use Platform.runLater since we're updating UI from potentially different thread
        Platform.runLater(() -> {
            logArea.appendText(log + "\n");
        });
    }
}