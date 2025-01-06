package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainPageController extends Application{

	public void start(Stage primaryStage) throws Exception {

    	//ClientUI.chat= new ChatClient(ClientUI.host, 5555,new ClientUI());
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/GUI/mainPage.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		

	}
	
	public static void main( String args[] ) throws Exception{ 
			
			
		    launch(args); 
	} // end main
}
