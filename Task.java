package application;

import java.util.Date;

public class Task {
	
	private int urgencyRating;
	private Date dateCreated;
	private Date dateDue;
	private Date dateCompleted;
	private String description;
	private String name;
	private int difficultyRating;
	private int timeToComplete;
	
	public Task(int urgencyRating, Date dateCreated, Date dateDue, Date dateCompleted, String description, String name, int difficultyRating, int timeToComplete){
		//initializes all needed task instance variables
		this.dateCreated = dateCreated;
		this.dateDue = dateDue;
		this.dateCompleted = dateCompleted;
		this.difficultyRating = difficultyRating;
		this.timeToComplete = timeToComplete;
		
		if(description.equals(""))
			this.description = "{No description given}";
		else
			this.description = description;
		
		if(name.equals(""))
			this.name = "Unnamed Task";
		else
			this.name = name;
		
		if(urgencyRating == 0)
			calculateUrgency();
		else
			this.urgencyRating = urgencyRating;
		
	}
	
	public Task(Date dateDue, String description, String name, int difficultyRating, int timeToComplete){//contructor for user created tasks
		this(0, new Date(), dateDue, new Date(0), description, name, difficultyRating, timeToComplete);
	}
	
	
	private void calculateUrgency() {//calculates an urgency rating between 0-100 based on weighted values of inputed factors
		int temp = difficultyRating * 6;
		temp += timeToComplete / 15;
		
		Date current = new Date();
		long hourDifference = (dateDue.getTime() - current.getTime()) / 3600000L;
		
		if(hourDifference <= 12)
			temp = 100;
		else if(hourDifference <= 48)
			temp += 50;
		else if(hourDifference <= 48)
			temp += 50;
		else if(hourDifference <= 96)
			temp += 30;
		else if(hourDifference <= 168)
			temp += 20;
		else if(hourDifference <= 336)
			temp += 10;
		
		if(temp > 100)
			temp = 99;
		
		urgencyRating = temp;
	}




	//Getters and Setters
	public int getUrgencyRating() {
		return urgencyRating;
	}
	public void setUrgencyRating(int urgencyRating) {
		this.urgencyRating = urgencyRating;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}
	public Date getDateCompleted() {
		return dateCompleted;
	}
	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDifficultyRating() {
		return difficultyRating;
	}
	public void setDifficultyRating(int difficultyRating) {
		this.difficultyRating = difficultyRating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimeToComplete() {
		return timeToComplete;
	}
	public void setTimeToComplete(int timeToComplete) {
		this.timeToComplete = timeToComplete;
	}

}
