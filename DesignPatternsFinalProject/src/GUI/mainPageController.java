package GUI;

import chainAbuseAPIConn.HttpController;
import chainAbuseAPIConn.Report;
import chainAbuseAPIConn.ReportExporter;
import chainAbuseLogManagement.LogManager;
import chainAbuseLogManagement.LogObserver;
import chainAbuseLogManagement.LogToFile;
import chainAbuseLogManagement.LogToUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

import java.lang.String;
import java.util.Optional;

import javafx.event.ActionEvent;

public class mainPageController extends Application {
	
	// ~~~~~~~~~~~ ~~~~~~~~~~~  General Attributes:  ~~~~~~~~~~~ ~~~~~~~~~~~
	
	private ConfigurationClass config;
	
	private HttpController httpController;
	
	private List<Report> resultList;
	
	private LogManager logManager;

	// ~~~~~~~~~~~ ~~~~~~~~~~~  FXML Assets:  ~~~~~~~~~~~ ~~~~~~~~~~~ 
	
	@FXML
	private TextField newAddressTxtBox;

	@FXML
	private Button AddToListBtn;

	@FXML
	private TableView<String> AddressToCheckTable;

	@FXML
	private TableColumn<String, String> AddressClmCheckTable;

	@FXML
	private TableColumn<String, Button> EditClmCheckTable;

	@FXML
	private TableColumn<String, Button> DeleteClmCheckTable;

	@FXML
	private Button clearTableBtn;

	@FXML
	private TextArea logTxtArea;

	@FXML
	private Button scanBtn;

	@FXML
	private TableView<Report> ResultsTable;

	@FXML
	private TableColumn<Report, String> AddressClmReportTable;

	@FXML
	private TableColumn<Report, String> NumOfAbusesClmReportTable;

	@FXML
	private TableColumn<Report, String> LinkClimReportTable;

	@FXML
	Button uploadFileBtn;

	@FXML
	private Button saveResultsBtn;
	
	// ~~~~~~~~~~~ ~~~~~~~~~~~  Page Functions:  ~~~~~~~~~~~ ~~~~~~~~~~~ 

	@FXML
	private void clearTable(ActionEvent event) {
		AddressToCheckTable.getItems().clear();
	}

	@FXML
	private void scan(ActionEvent event) {
		List<String> addresses = AddressToCheckTable.getItems();
		try {
			resultList.clear();
			resultList = httpController.fetchAllReports(addresses);
			// Clear the results table and add new items
			ResultsTable.getItems().clear();
			ResultsTable.getItems().addAll(resultList);
			// Clear the addresses table
			AddressToCheckTable.getItems().clear();
			logManager.notifyObservers(createLogMessage("scan completed successfully"));
		} catch (Exception e) { // TODO: check type of exceptions
			logManager.notifyObservers(createLogMessage("scan failed: " + e.getMessage()));
		}
	}

	@FXML
	private void UploadFile(ActionEvent event) {
		// Create a FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Address File");

		// Set filter for .txt files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File selectedFile = fileChooser.showOpenDialog(uploadFileBtn.getScene().getWindow());

		if (selectedFile != null) {
			try {
				// Read file line by line
				List<String> addresses = Files.readAllLines(selectedFile.toPath());

				// Create Report object for each address and add to table
				for (String address : addresses) {
					if (!address.trim().isEmpty()) { // Skip empty lines
						AddressToCheckTable.getItems().add(address.trim());
					}
				}
			} catch (IOException e) {
				// Show error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("File Reading Error");
				alert.setContentText("Could not read the file: " + e.getMessage());
				alert.showAndWait();
			}
		}
	}

	@FXML
	private void saveResults(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Report Results");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

		File file = fileChooser.showSaveDialog(saveResultsBtn.getScene().getWindow());

		if (file != null) {
			try {
				ReportExporter.exportToCSV(ResultsTable.getItems(), file.getAbsolutePath());

				// Show success message
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText(null);
				alert.setContentText("Report saved successfully!");
				alert.showAndWait();

			} catch (IOException e) {
				// Show error message
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Save Failed");
				alert.setContentText("Could not save the file: " + e.getMessage());
				alert.showAndWait();
			}
		}
	}

	@FXML
	private void addToList(ActionEvent event) {
		String newAddress = newAddressTxtBox.getText();
		if (!newAddress.isEmpty()) {

			// Add to table
			AddressToCheckTable.getItems().add(newAddress);

			// Clear the text field
			newAddressTxtBox.clear();
		}
	}

	private void showEditDialog(String address, int index) {
		// Create a new dialog
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Edit Address");
		dialog.setHeaderText("Edit the address for this row");

		// Set the button types
		ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

		// Create the address field and set its initial value
		TextField addressField = new TextField(address);

		// Enable/Disable save button depending on whether a address was entered
		Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
		saveButton.setDisable(true);

		// Validate the address field
		addressField.textProperty().addListener((observable, oldValue, newValue) -> {
			saveButton.setDisable(newValue.trim().isEmpty());
		});

		// Layout the dialog
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		grid.add(new Label("Address:"), 0, 0);
		grid.add(addressField, 1, 0);

		dialog.getDialogPane().setContent(grid);

		// Request focus on the address field by default
		Platform.runLater(() -> addressField.requestFocus());

		// Convert the result to a string when the save button is clicked
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == saveButtonType) {
				return addressField.getText();
			}
			return null;
		});

		// Show the dialog and handle the result
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(newAddress -> {
			// Update the table
			AddressToCheckTable.getItems().set(index, newAddress);
		});
	}

	private String createLogMessage(String message) {
		return String.format("%s - %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss")),
				message);
	}

	@FXML
	private void initialize() {
		// initialize attributes
		config = ConfigurationClass.getConfig();
		httpController = HttpController.getInstance();
		resultList = new ArrayList<>();
		logManager = new LogManager();
		logManager.attach(new LogToFile(config.getLogFilePath()));
		logManager.attach(new LogToUI(logTxtArea));
		logManager.notifyObservers(createLogMessage("Program started running"));

		// Set up the address column
		AddressClmCheckTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

		// Set up the Edit column
		EditClmCheckTable.setCellFactory(param -> new TableCell<String, Button>() {
			private final Button editButton = new Button("Edit");

			@Override
			protected void updateItem(Button item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(editButton);
					editButton.setOnAction(event -> {
						String address = getTableView().getItems().get(getIndex());
						showEditDialog(address, getIndex());
					});
				}
			}
		});

		// Set up the Delete column
		DeleteClmCheckTable.setCellFactory(param -> new TableCell<String, Button>() {
			private final Button deleteButton = new Button("Delete");

			@Override
			protected void updateItem(Button item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteButton);
					deleteButton.setOnAction(event -> {
						String address = getTableView().getItems().get(getIndex());
						AddressToCheckTable.getItems().remove(address);
					});
				}
			}
		});

		// Initialize the table
		AddressToCheckTable.getItems().clear();

		// Connect the button to the method
		AddToListBtn.setOnAction(this::addToList);

		// Set up Results table columns
		AddressClmReportTable
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));

		NumOfAbusesClmReportTable.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAbuseCount())));

		LinkClimReportTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLink()));
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/GUI/mainPage.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public static void main(String args[]) throws Exception {

		launch(args);
	}
}
