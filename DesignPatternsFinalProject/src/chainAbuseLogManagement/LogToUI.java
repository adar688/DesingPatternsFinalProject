package chainAbuseLogManagement;

public class LogToUI extends LogObserver{

	@Override
	public void update(String log) {
		System.out.println("Log To UI" + log);
		
	}

}
