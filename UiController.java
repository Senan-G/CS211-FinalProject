package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class UiController implements Initializable{//Controller for view.fxml
	
	protected TaskCollector taskCollector;//defines task collectors for keeping track of tasks
	protected TaskCollector completedCollector;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		taskCollector = new TaskCollector();
		completedCollector  = new TaskCollector();
		ArrayList<Task> temp = new ArrayList<Task>();
		temp.add(new Task(new Date(1575338400574L + (5L * 3600000L * 24L)), "hello", "1", 4, 1));
		temp.add(new Task(new Date(1575338400574L + (2L * 3600000L * 24L)), "hello", "2", 2, 2));
		temp.add(new Task(new Date(1575338400574L + (32L * 3600000L * 24L)), "hello", "3", 8, 3));
		temp.add(new Task(new Date(1575338400574L + (8L * 3600000L * 24L)), "hello", "4", 34, 5));
		temp.add(new Task(new Date(1575338400574L + (12L * 3600000L * 24L)), "hello", "5", 7, 4));
		temp.add(new Task(new Date(1575338400574L + (5L * 3600000L * 24L)), "hello", "6", 54, 6));
		temp.add(new Task(new Date(1575338400574L + (66L * 3600000L * 24L)), "hello", "7", 9, 7));
		temp.add(new Task(new Date(1575338400574L + (78L * 3600000L * 24L)), "hello", "8", 2, 8));
		temp.add(new Task(new Date(1575338400574L + (2L * 3600000L * 24L)), "hello", "9", 86, 9));
		temp.add(new Task(new Date(1575338400574L + (3L * 3600000L * 24L)), "hello", "10", 6, 1));
		taskCollector.setTasks(temp);
		*/
		
		taskCollector = FileParser.readFile();//reads in save file and updates ui
		completedCollector = new TaskCollector();
		updateList();
		
	}
	
	public void updateList(){
		taskList.getItems().clear();//clears array
		
		for(Task task : taskCollector.getTasks()){//shows urgency if enabled, otherwise just shows task name for each task
			
			String temp = "";
			if(taskCollector.isShowUrgency())
				temp += task.getUrgencyRating() + " | ";
			
			temp += task.getName();
			
			taskList.getItems().add(temp);
		}
	}
	
	public void updateCompletedList(){//updates completed tasks ui
		completedList.getItems().clear();
		for(Task task : completedCollector.getTasks())
			completedList.getItems().add(task.getName());
	}
	
	
	//define all ui elements
    @FXML
    protected ListView<String> taskList;

    @FXML
    private Button newTask;

    @FXML
    private Button sortBy;

    @FXML
    private ListView<String> completedList;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private Label completedToday;

    @FXML
    private Label completedWeek;

    @FXML
    private Label completedTotal;

    @FXML
    private Label avgDay;

    @FXML
    private Label avgWeek;

    @FXML
    private CheckBox showUrgency;

    @FXML
    private CheckBox allowDeletion;

    @FXML
    private Button saveButton;
    
    //define all ui actions
    @FXML
    void addNewTask() {//loads new task popup and sends data
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTaskPopup.fxml"));
        	Parent root1 = (Parent) fxmlLoader.load();
        	NewTaskController newTaskController = fxmlLoader.getController();
    		newTaskController.passInfo(this);
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root1));
        	stage.show();
    	}
    	catch(Exception e){
    		System.out.println("NewTaskPopup.fxml not loaded");
    		e.printStackTrace();
    	}
    }

    @FXML
    void allowTaskDeletion() {//changes allow deletion setting
    	if(taskCollector.isAllowDeletion())
    		taskCollector.setAllowDeletion(false);
    	else
    		taskCollector.setAllowDeletion(true);
    }

    @FXML
    void handleSaveClick() {//saves changes to file
    	FileParser.writeFile(taskCollector);
    }

    @FXML
    void showUrgencyList() {//changes show urgency setting and updates list ui
    	if(taskCollector.isShowUrgency())
    		taskCollector.setShowUrgency(false);
    	else
    		taskCollector.setShowUrgency(true);
    	
    	updateList();
    }

    @FXML
    void sortOptions() {//loads sort list popup and sends data
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SortByPopup.fxml"));
        	Parent root1 = (Parent) fxmlLoader.load();
        	SortByController sortByController = fxmlLoader.getController();
        	sortByController.passInfo(this);
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root1));
        	stage.show();
    	}
    	catch(Exception e){
    		System.out.println("no");
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void handleListViewClick() {//loads task info popup and sends data, when list is clicked
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskInfoPopup.fxml"));
        	Parent root1 = (Parent) fxmlLoader.load();
        	TaskInfoController taskInfoController = fxmlLoader.getController();
        	taskInfoController.passInfo(this);
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root1));
        	stage.show();
    	}
    	catch(Exception e){
    		System.out.println("yeet");
    		e.printStackTrace();
    	}
    }



}
