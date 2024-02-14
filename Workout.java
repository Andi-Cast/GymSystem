//Workout.java

import java.io.*;
import java.util.*; 

class Workout implements Serializable {
	protected String creater;
	protected String name;
	protected String date;
	protected String completion;
	protected ArrayList<Exercise> exerciseList;
	
	Workout() {
		creater = "unknown";
		name = "back";
		date = "00/00/00";
		completion = "completed";
		exerciseList = new ArrayList<>();
	}//end Workout

	Workout(String creater,String name, String date, String completion) {
		this.creater = creater;
		this.name = name;
		this.date = date;
		this.completion = completion;
		exerciseList = new ArrayList<>();	
	}//end Workout()

	
	void setCreater(String creater){
        	this.creater = creater;
    	}//end setCreater
    
    	String getCreater(){
        	return creater;
    	}//end getCreater
    
    	void setName(String name){
        	this.name = name;
   	}//end setName
    
    	String getName() {
        	return name;
    	}//end getName
    
   	void setDate(String date){
        	this.date = date;
    	}//end setDate
    
    	String getDate() {
        	return date;
    	}//end getDate	
    
    	void setCompletion(String completion){
        	this.completion = completion;
    	}//end setCompletion
    
    	String getCompletion(){
        	return completion;
    	}//end getCompletion

	void addExercise() {
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);
		System.out.println("------------------------------");
		System.out.print("Enter name of exercise: ");
		String name = inputString.nextLine();
		System.out.print("Enter number of sets: ");
		int sets = input.nextInt();
		System.out.print("Enter number of reps: ");
		int reps = input.nextInt();
		Exercise exercise = new Exercise(name, sets, reps);
		exerciseList.add(exercise);	
	}//end addExercise

	void listExercises() {
		Iterator<Exercise> i = exerciseList.iterator();
		System.out.println("Exercise | Sets | Reps");
		while(i.hasNext()) {
			Exercise exercise = i.next();
			System.out.println(exercise.getInfo());
		}
	}//end listExercises

	String getInfo() {
		return name + " | " + date + " | " + creater + " | " + completion;
	}//end getInfo
}//end Workout class
