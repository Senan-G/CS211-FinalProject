package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;

public class NewTaskController {
	
	private UiController main;
	
	public void passInfo(UiController main){//collects main ui to access instance elements
		this.main = main;
	}
	
	//define all ui elements
    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField name;

    @FXML
    private TextField desc;

    @FXML
    private TextField due;

    @FXML
    private Slider difficulty;

    @FXML
    private Slider timeTocomplete;
    
    //define all ui actions
    @FXML
    void handleCancelClick() {//closes stage without further action
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCreateClick() {
    	
    	Date dueDate = new Date();
    	
    	try{//catches non-number values
    		dueDate = new Date(new Date().getTime() + (Long.parseLong(due.getText()) * 86400000L));//converts days into milliseconds away from current time
    	}
    	catch(Exception e){
    		badValueError();
    		return;
    	}
    	
    	Task task = new Task(dueDate, desc.getText(), name.getText(), (int) difficulty.getValue(), (int) timeTocomplete.getValue());//creates new task based on inputed values
    	
        main.taskCollector.getTasks().add(task);//adds task to list, updates ui and saves to file
        FileParser.writeFile(main.taskCollector);
    	main.updateList();
    	
    	handleCancelClick();//closes popup
    }
    
    private void badValueError(){
    	
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BadValuePopup.fxml"));
        	Parent root1 = (Parent) fxmlLoader.load();
        	Stage stage = new Stage();
        	stage.setTitle("Bad Value Error");
        	stage.setScene(new Scene(root1));
        	stage.show();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }

}
