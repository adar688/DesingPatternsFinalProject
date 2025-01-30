package chainAbuseLogManagement;

import java.util.ArrayList;
import java.util.List;

public class LogManager {

	private List<LogObserver> observers;
	
	public LogManager() {
		observers = new ArrayList<LogObserver>();
	}
	
	public void attach(LogObserver observer) {
		observers.add(observer);
	}
	
	public void notifyObservers(String log) {
		for(LogObserver observer: observers) {
			observer.update(log);
		}
	}
}
