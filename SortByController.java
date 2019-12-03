package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SortByController {
	
	private UiController main;
	
	public void passInfo(UiController main){//collects main ui for instance variable use
		this.main = main;
	}

	//define all ui elements
    @FXML
    private Button urgencyButton;

    @FXML
    private Button dueDateButton;

    @FXML
    private Button dateAddedButton;

    @FXML
    private Button difficultyButton;

    @FXML
    private Button completeButton;

    @FXML
    private Button cancelButton;

    //define all ui actions
    @FXML
    void handleAddedClick() {//sorts by date added and updates ui
    	main.taskCollector.sortByDateAdded();
    	main.updateList();
    	
    	handleCancelClick();
    }

    @FXML
    void handleCancelClick() {//closes ui
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCompleteButton() {//sorts by date completed and updates ui
    	main.taskCollector.sortByTimeToComplete();
    	main.updateList();
    	
    	handleCancelClick();
    }

    @FXML
    void handleDifficultyClick() {//sorts by difficulty and updates ui
    	main.taskCollector.sortByDifficulty();
    	main.updateList();
    	
    	handleCancelClick();
    }

    @FXML
    void handleUrgencyClick() {//sorts by urgency rating and updates ui
    	main.taskCollector.sortByUrgency();
    	main.updateList();
    	
    	handleCancelClick();
    }
    
    @FXML
    void handleDueClick() {//sorts by date due and updates ui
    	main.taskCollector.sortByDateDue();
    	main.updateList();
    	
    	handleCancelClick();
    }

}
