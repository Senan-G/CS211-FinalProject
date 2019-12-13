package application;

import java.util.ArrayList;

public class TaskCollector {
	
	private ArrayList<Task> tasks;
	private ArrayList<Task> completed;
	private boolean showUrgency;
	private boolean colorCoding;
	private boolean allowDeletion;
	
	public TaskCollector(){
		this(new ArrayList<Task>(), new ArrayList<Task>(), false, false, false);
	}
	
	public TaskCollector(ArrayList<Task> tasks, ArrayList<Task> completed){
		this(tasks, completed, false, false, false);
	}
	
	public TaskCollector(ArrayList<Task> tasks, ArrayList<Task> completed, boolean showUrgency, boolean colorCoding, boolean allowDeletion){//initializes all settings and task list
		this.tasks = tasks;
		this.completed = completed;
		this.setShowUrgency(showUrgency);
		this.setColorCoding(colorCoding);
		this.setAllowDeletion(allowDeletion);
	}
	
	public void sortByUrgency(){//kicks off merge sort based on urgency rating
		divide(0, tasks.size()-1, "urgency");
	}
	
	public void sortByDateDue(){//kicks off merge sort based on date due
		divide(0, tasks.size()-1, "dueDate");
	}
	
	public void sortByDateAdded(){//kicks off merge sort based on date added
		divide(0, tasks.size()-1, "dateAdded");
	}
	
	public void sortByDifficulty(){//kicks off merge sort based on difficulty rating
		divide(0, tasks.size()-1, "difficulty");
	}
	
	public void sortByTimeToComplete(){//kicks off merge sort based on estimated time to complete
		divide(0, tasks.size()-1, "TimeToComplete");
	}
	
	public void divide(int startIndex,int endIndex, String keyword){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid, keyword);
            divide(mid+1, endIndex, keyword);        
            
            merge(startIndex,mid,endIndex, keyword);     
        }       
    }
	
	public void merge(int startI, int midI, int endI, String keyword){//sorts and merges divided array back together based on keyword comparison
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(compare(keyword, tasks.get(leftIndex), tasks.get(rightIndex)))
            	tempArray.add(tasks.get(leftIndex++));
            else
            	tempArray.add(tasks.get(rightIndex++));
        }       
        
        
        while(leftIndex <= midI)
        	tempArray.add(tasks.get(leftIndex++));
        
        while(rightIndex <= endI)
        	tempArray.add(tasks.get(rightIndex++));
        
        int i = 0;
        int j = startI;

        while(tempArray.size() > i)
        	tasks.set(j++, tempArray.get(i++));
        
        
    }
	
	private boolean compare(String keyword, Task leftTask, Task rightTask){
		
		if(keyword.equals("urgency")){
			return leftTask.getUrgencyRating() >= rightTask.getUrgencyRating();
		}
		else if(keyword.equals("dueDate")){
			return leftTask.getDateDue().before(rightTask.getDateDue()) || leftTask.getDateDue().equals(rightTask.getDateDue());
		}
		else if(keyword.equals("dateAdded")){
			return leftTask.getDateCreated().before(rightTask.getDateCreated()) || leftTask.getDateCreated().equals(rightTask.getDateCreated());
		}
		else if(keyword.equals("difficulty")){
			return leftTask.getDifficultyRating() <= rightTask.getDifficultyRating();
		}
		else if(keyword.equals("TimeToComplete")){
			return leftTask.getTimeToComplete() <= rightTask.getTimeToComplete();
		}
		
		return false;
	}
	
	/*
	
	public void divideDateDue(int startIndex,int endIndex){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideDateDue(startIndex, mid);
            divideDateDue(mid+1, endIndex);        
            
            mergeDateDue(startIndex,mid,endIndex);  
        }       
    }
	
	public void mergeDateDue(int startI, int midI, int endI){//sorts and merges divided array back together based on date due
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(tasks.get(leftIndex).getDateDue().before(tasks.get(rightIndex).getDateDue()) || tasks.get(leftIndex).getDateDue().equals(tasks.get(rightIndex).getDateDue()))
            	tempArray.add(tasks.get(leftIndex++));
            else
            	tempArray.add(tasks.get(rightIndex++));
        }       
        
        
        while(leftIndex <= midI)
        	tempArray.add(tasks.get(leftIndex++));
        
        while(rightIndex <= endI)
        	tempArray.add(tasks.get(rightIndex++));
        
        int i = 0;
        int j = startI;

        while(tempArray.size() > i)
        	tasks.set(j++, tempArray.get(i++));
        
        
    }
	
	
	
	public void divideDateAdded(int startIndex,int endIndex){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideDateAdded(startIndex, mid);
            divideDateAdded(mid+1, endIndex);        
            
            mergeDateAdded(startIndex,mid,endIndex);  
        }       
    }
	
	public void mergeDateAdded(int startI, int midI, int endI){//sorts and merges divided array back together based on date added
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(tasks.get(leftIndex).getDateCreated().before(tasks.get(rightIndex).getDateCreated()) || tasks.get(leftIndex).getDateCreated().equals(tasks.get(rightIndex).getDateCreated()))
            	tempArray.add(tasks.get(leftIndex++));
            else
            	tempArray.add(tasks.get(rightIndex++));
        }       
        
        
        while(leftIndex <= midI)
        	tempArray.add(tasks.get(leftIndex++));
        
        while(rightIndex <= endI)
        	tempArray.add(tasks.get(rightIndex++));
        
        int i = 0;
        int j = startI;

        while(tempArray.size() > i)
        	tasks.set(j++, tempArray.get(i++));
        
        
    }
	
	
	
	public void divideDifficulty(int startIndex,int endIndex){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideDifficulty(startIndex, mid);
            divideDifficulty(mid+1, endIndex);        
            
            mergeDifficulty(startIndex,mid,endIndex);            
        }       
    }
	
	public void mergeDifficulty(int startI, int midI, int endI){//sorts and merges divided array back together based on difficulty rating
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(tasks.get(leftIndex).getDifficultyRating() <= tasks.get(rightIndex).getDifficultyRating())
            	tempArray.add(tasks.get(leftIndex++));
            else
            	tempArray.add(tasks.get(rightIndex++));
        }       
        
        
        while(leftIndex <= midI)
        	tempArray.add(tasks.get(leftIndex++));
        
        while(rightIndex <= endI)
        	tempArray.add(tasks.get(rightIndex++));
        
        int i = 0;
        int j = startI;

        while(tempArray.size() > i)
        	tasks.set(j++, tempArray.get(i++));
        
        
    }
	
	
	
	public void divideTimeToComplete(int startIndex,int endIndex){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideTimeToComplete(startIndex, mid);
            divideTimeToComplete(mid+1, endIndex);        
            
            mergeTimeToComplete(startIndex,mid,endIndex);            
        }       
    }
	
	public void mergeTimeToComplete(int startI, int midI, int endI){//sorts and merges divided array back together based on estimated time to complete
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(tasks.get(leftIndex).getTimeToComplete() <= tasks.get(rightIndex).getTimeToComplete())
            	tempArray.add(tasks.get(leftIndex++));
            else
            	tempArray.add(tasks.get(rightIndex++));
        }       
        
        
        while(leftIndex <= midI)
        	tempArray.add(tasks.get(leftIndex++));
        
        while(rightIndex <= endI)
        	tempArray.add(tasks.get(rightIndex++));
        
        int i = 0;
        int j = startI;

        while(tempArray.size() > i)
        	tasks.set(j++, tempArray.get(i++));
        
        
    }
	*/
	
	
	//getters and setters
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public boolean isShowUrgency() {
		return showUrgency;
	}

	public void setShowUrgency(boolean showUrgency) {
		this.showUrgency = showUrgency;
	}

	public boolean isColorCoding() {
		return colorCoding;
	}

	public void setColorCoding(boolean colorCoding) {
		this.colorCoding = colorCoding;
	}

	public boolean isAllowDeletion() {
		return allowDeletion;
	}

	public void setAllowDeletion(boolean allowDeletion) {
		this.allowDeletion = allowDeletion;
	}

	public ArrayList<Task> getCompleted() {
		return completed;
	}

	public void setCompleted(ArrayList<Task> completed) {
		this.completed = completed;
	}
	
	
}
