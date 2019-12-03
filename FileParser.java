package application;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileParser {
	
	public static TaskCollector readFile(){
		
		//initializes data to be read and filled
		boolean showUrgencyValue = false;
		boolean colorCoding = false;
		boolean allowDeletion = false;
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		File file = new File("/Users/senan/OneDrive/Desktop/CS211/Projects/Final/src/application/save.txt");//creates new file object from save.txt
		Scanner scnr = null;
		
		try{
			scnr = new Scanner(file);//reads in file
		}
		catch(Exception e){
			System.out.println("Load File Failed");
			System.out.println(e);
			return new TaskCollector();
		}
		
		//collects saved data for application settings
		showUrgencyValue = scnr.nextBoolean();
		colorCoding = scnr.nextBoolean();
		allowDeletion = scnr.nextBoolean();
		
		//iterates through remaining data which will be parsed and saved into an array of tasks
		while(scnr.hasNextLine()){
			int urgencyRating;
			try{//makes sure next line is correct value
				urgencyRating = scnr.nextInt();
			}
			catch(Exception e){
				break;
			}
			Date dateCreated = new Date(scnr.nextLong());
			Date dateDue = new Date(scnr.nextLong());
			Date dateCompleted = new Date(scnr.nextLong());
			scnr.nextLine();//completes line so so next will be actual data
			String description = scnr.nextLine();
			String name = scnr.nextLine();
			int difficultyRating = scnr.nextInt();
			int timeToComplete = scnr.nextInt();
			
			Task task = new Task(urgencyRating, dateCreated, dateDue, dateCompleted, description, name, difficultyRating, timeToComplete);//creates new task based on parsed data and saves into array
			tasks.add(task);
		}
			
		//returns taskcollector based on collected save data
		return new TaskCollector(tasks, showUrgencyValue, colorCoding, allowDeletion);
	}
	
	public static boolean writeFile(TaskCollector taskCollector){
		
		
		try{
			FileWriter fileWriter = new FileWriter("/Users/senan/OneDrive/Desktop/CS211/Projects/Final/src/application/save.txt");//creates new file writer for save.txt
			
			//writes all applications settings to file
			fileWriter.write(taskCollector.isShowUrgency() + "\r\n");
			fileWriter.write(taskCollector.isColorCoding() + "\r\n");
			fileWriter.write(taskCollector.isAllowDeletion() + "\r\n");
			
			//iterates through task objects and splits it into needed information and saves it
			for(Task task : taskCollector.getTasks()){
				fileWriter.write(task.getUrgencyRating() + "\r\n");
				fileWriter.write(task.getDateCreated().getTime() + "\r\n");
				fileWriter.write(task.getDateDue().getTime() + "\r\n");
				fileWriter.write(task.getDateCompleted().getTime() + "\r\n");
				fileWriter.write(task.getDescription() + "\r\n");
				fileWriter.write(task.getName() + "\r\n");
				fileWriter.write(task.getDifficultyRating() + "\r\n");
				fileWriter.write(task.getTimeToComplete() + "\r\n");
			}
			fileWriter.close();
		}
		catch(Exception e){
			System.out.println("Write to File Failed");
			System.out.println(e);
			return false;
		}
		
		return true;
	}

}
