package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class mainPageController<T> extends Application{
	
	@FXML
	private TextField newAddressTxtBox;
	
	@FXML
	private Button AddToListBtn;
	
	@FXML
	private TableView<T> AddressToCheckTable;
	
	@FXML 
	private TableColumn<T,T> AddressClmCheckTable;
	
	@FXML 
	private TableColumn<T,T> EditClmCheckTable;
	
	@FXML 
	private TableColumn<T,T> DeleteClmCheckTable;
	
	
	@FXML
	private Button clearTableBtn;
	
	@FXML
	private TextArea logTxtArea;
	
	@FXML
	private Button scanBtn;
	
	@FXML
	private TableView<T> ResultsTable;
	
	@FXML 
	private TableColumn<T,T> AddressClmReportTable;
	
	@FXML 
	private TableColumn<T,T> NumOfAbusesClmReportTable;
	
	@FXML 
	private TableColumn<T,T> LinkClimReportTable;
	
	@FXML
	Button uploadFileBtn;
	
	@FXML
	private Button saveResultsBtn;
	
	

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
