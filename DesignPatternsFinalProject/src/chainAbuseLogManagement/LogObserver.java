package chainAbuseLogManagement;

public abstract class LogObserver {

	protected LogManager logManager;
	public abstract void update(String log);
}
