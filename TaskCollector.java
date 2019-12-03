package application;

import java.util.ArrayList;

public class TaskCollector {
	
	private ArrayList<Task> tasks;
	private boolean showUrgency;
	private boolean colorCoding;
	private boolean allowDeletion;
	
	public TaskCollector(){
		this(new ArrayList<Task>(), false, false, false);
	}
	
	public TaskCollector(ArrayList<Task> tasks){
		this(tasks, false, false, false);
	}
	
	public TaskCollector(ArrayList<Task> tasks, boolean showUrgency, boolean colorCoding, boolean allowDeletion){//initializes all settings and task list
		this.tasks = tasks;
		this.setShowUrgency(showUrgency);
		this.setColorCoding(colorCoding);
		this.setAllowDeletion(allowDeletion);
	}
	
	public void sortByUrgency(){//kicks off merge sort based on urgency rating
		divideUrgency(0, tasks.size()-1);
	}
	
	public void divideUrgency(int startIndex,int endIndex){//divides array recursively
        
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideUrgency(startIndex, mid);
            divideUrgency(mid+1, endIndex);        
            
            mergeUrgency(startIndex,mid,endIndex);            
        }       
    }
	
	public void mergeUrgency(int startI, int midI, int endI){//sorts and merges divided array back together based on urgency
        
        ArrayList<Task> tempArray = new ArrayList<Task>();
        
        int leftIndex = startI;
        int rightIndex = midI+1;
        
        while(leftIndex <= midI && rightIndex <= endI){
            if(tasks.get(leftIndex).getUrgencyRating() >= tasks.get(rightIndex).getUrgencyRating())
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
	
	public void sortByDateDue(){//kicks off merge sort based on date due
		divideDateDue(0, tasks.size()-1);
	}
	
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
	
	public void sortByDateAdded(){//kicks off merge sort based on date added
		divideDateAdded(0, tasks.size()-1);
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
	
	public void sortByDifficulty(){//kicks off merge sort based on difficulty rating
		divideDifficulty(0, tasks.size()-1);
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
	
	public void sortByTimeToComplete(){//kicks off merge sort based on estimated time to complete
		divideTimeToComplete(0, tasks.size()-1);
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
	
	
}
