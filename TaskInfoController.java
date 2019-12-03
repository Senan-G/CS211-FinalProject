package application;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TaskInfoController {
	
	private UiController main;
	private Task task;
	
	public void passInfo(UiController main){
		this.main = main;//collects main ui for instance variable use
		
		for(int i = 0; i < main.taskCollector.getTasks().size(); i++)//iterates through task list and finds the task that corresponds to the selected index on the ui
			if(main.taskList.getSelectionModel().getSelectedItem().contains(main.taskCollector.getTasks().get(i).getName()))
				task = main.taskCollector.getTasks().get(i);
		
		//sets all labels text to found task info
		nameLabel.setText(task.getName());
		descLabel.setText(task.getDescription());
		daysLabel.setText("" + ((task.getDateDue().getTime() - new Date().getTime()) / 86400000));
		urgencyLabel.setText("" + task.getUrgencyRating());
		
		if(!main.taskCollector.isAllowDeletion())//disables button if setting applicable
			removeButton.setDisable(true);
	} 

	//define all ui elements
    @FXML
    private Label nameLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Label daysLabel;

    @FXML
    private Label urgencyLabel;

    @FXML
    private Button completeButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button cancelButton;

    //define all ui actions
    @FXML
    void handleCancelClick() {//closes ui
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCompleteClick() {//moves selected task from incomplete to complete
    	if(main.taskCollector.getTasks().remove(task))
    		main.completedCollector.getTasks().add(task);
    	
    	main.updateCompletedList();//updates lists
    	main.updateList();
    	
    	handleCancelClick();//closes ui
    }

    @FXML
    void handleRemoveClick() {//removes task from list without moving it to completed
    	main.taskCollector.getTasks().remove(task);
    	main.updateList();
    	
    	handleCancelClick();//closes ui
    }

}
