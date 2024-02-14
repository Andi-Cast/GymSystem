//Exercise.java

import java.io.*;

class Exercise implements Serializable{
	protected String name;
	protected int reps;
	protected int sets;
	
	Exercise() {
		name = "unknown exercise";
		reps = 0;
		sets = 0;
	}//end Exercise

	Exercise(String name, int sets, int reps) {
		this.name = name;
		this.sets= sets;
		this.reps = reps;
	}//end Exercise()

	void setName(String name) {
		this.name = name;
	}//end setName

	String getName() {
		return name;
	}//end getName 

	void setReps(int reps) {
		this.reps = reps;
	}//end setReps

	int getReps() {
		return reps;
	}//end getReps

	void setSets(int sets) {
		this.sets = sets;
	}//end setSets

	int getSets() {
		return sets;
	}//end getSets

	String getInfo() {
		return name + " | " + sets + " | " + reps;
	}//end getInfo
}//end class Exercise
