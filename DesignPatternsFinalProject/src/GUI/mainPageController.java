package GUI;

import chainAbuseAPIConn.Report;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.String;
import javafx.event.ActionEvent;

public class mainPageController extends Application{
	
	@FXML
	private TextField newAddressTxtBox;
	
	@FXML
	private Button AddToListBtn;
	
	@FXML
	private TableView<Report> AddressToCheckTable;
	
	@FXML 
	private TableColumn<Report,String> AddressClmCheckTable;
	
	@FXML 
	private TableColumn<Report,Button> EditClmCheckTable;
	
	@FXML 
	private TableColumn<Report,Button> DeleteClmCheckTable;
	
	@FXML
	private Button clearTableBtn;
	
	@FXML
	private TextArea logTxtArea;
	
	@FXML
	private Button scanBtn;
	
	@FXML
	private TableView<Report> ResultsTable;
	
	@FXML 
	private TableColumn<Report,String> AddressClmReportTable;
	
	@FXML 
	private TableColumn<Report,Integer> NumOfAbusesClmReportTable;
	
	@FXML 
	private TableColumn<Report,String> LinkClimReportTable;
	
	@FXML
	Button uploadFileBtn;
	
	@FXML
	private Button saveResultsBtn;
	
	@FXML
	private void clearTable(ActionEvent event) {
	    // Implementation for clearTable button
	}

	
	@FXML
	private void scan(ActionEvent event) {
	    // Implementation for scan button
	}

	@FXML
	private void UploadFile(ActionEvent event) {
	    // Implementation for uploadFileBtn
	}

	@FXML
	private void saveResults(ActionEvent event) {
	    // Implementation for saveResultsBtn
	}
	
	@FXML
	private void addToList(ActionEvent event) {
	    String newAddress = newAddressTxtBox.getText();
	    if(!newAddress.isEmpty()) {
	        // Create new Report object
	        Report report = new Report(newAddress);
	        
	        // Add to table
	        AddressToCheckTable.getItems().add(report);
	        
	        // Clear the text field
	        newAddressTxtBox.clear();
	    }
	}
	
	
	@FXML
	private void initialize() {
	    // Set up the address column
	    AddressClmCheckTable.setCellValueFactory(cellData -> 
	        new SimpleStringProperty(cellData.getValue().getAddress()));
	        
	    // Set up the Edit and Delete columns
	    EditClmCheckTable.setCellFactory(param -> new TableCell<Report, Button>() {
	        private final Button editButton = new Button("Edit");
	        
	        @Override
	        protected void updateItem(Button item, boolean empty) {
	            super.updateItem(item, empty);
	            
	            if (empty) {
	                setGraphic(null);
	            } else {
	                setGraphic(editButton);
	                editButton.setOnAction(event -> {
	                    Report report = getTableView().getItems().get(getIndex());
	                    // Add edit functionality here
	                });
	            }
	        }
	    });
	    
	    DeleteClmCheckTable.setCellFactory(param -> new TableCell<Report, Button>() {
	        private final Button deleteButton = new Button("Delete");
	        
	        @Override
	        protected void updateItem(Button item, boolean empty) {
	            super.updateItem(item, empty);
	            
	            if (empty) {
	                setGraphic(null);
	            } else {
	                setGraphic(deleteButton);
	                deleteButton.setOnAction(event -> {
	                    Report report = getTableView().getItems().get(getIndex());
	                    AddressToCheckTable.getItems().remove(report);
	                });
	            }
	        }
	    });
	    
	    // Initialize the table
	    AddressToCheckTable.getItems().clear();
	    
	    // Connect the button to the method
	    AddToListBtn.setOnAction(this::addToList);
	}


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
