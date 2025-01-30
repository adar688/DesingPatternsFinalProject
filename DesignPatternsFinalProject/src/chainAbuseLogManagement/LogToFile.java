package chainAbuseLogManagement;

public class LogToFile extends LogObserver{

	@Override
	public void update(String log) {
		System.out.println("Log to file" + log);
	}

}
