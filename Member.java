//Member.java

import java.io.*;
import java.util.*;

class Member extends Person implements Serializable {
	protected String trainer;
	protected int yearsOfExp;
	protected int bench;
	protected int squat;
	protected int deadlift;
	protected ArrayList<Workout> workoutList;	
	protected ArrayList<Message> messageList;	
	
	Member() {
		trainer = "none";
		name = "unknown";
		password = 00000;
		accountStatus = "Active";
		age = 0;
		trainer = "none";
		yearsOfExp = 0;
		bench = 0;
		squat = 0;
		deadlift = 0;
		workoutList = new ArrayList<>();
		messageList = new ArrayList<>();
	}//end Member

	Member(String name, int password, int age, String trainer, int yearsOfExp, int bench, int squat, int deadlift) {
		this.name = name;
		this.password = password;
		accountStatus = "Active";
		this.age = age;
		this.trainer = trainer;
		this.yearsOfExp = yearsOfExp;
		this.bench = bench;
		this.squat = squat;
		this.deadlift = deadlift;
		workoutList = new ArrayList<>();
		messageList = new ArrayList<>();
	}//end Member() 

	Member(String name, int password, String accountStatus, int age, String trainer, int yearsOfExp, int bench, int squat, int deadlift, ArrayList<Workout> workoutList, ArrayList<Message> messageList) {
                this.name = name;
                this.password = password;
                this.accountStatus = accountStatus;
                this.age = age;
		this.trainer = trainer;
                this.yearsOfExp = yearsOfExp;
                this.bench = bench;
                this.squat = squat;
                this.deadlift = deadlift;
                this.workoutList = workoutList;
                this.messageList = messageList;
        }//end Member() 

	void setTrainer(String trainer) {
		this.trainer = trainer;
	}//end setTrainer

	String getTrainer() {
		return trainer;
	}//end getTrainer

	void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}//end setYearsOfExp

	int getYearsOfExp() {
		return yearsOfExp;
	}//end getYearsOfExp

	void setBench(int bench) {
		this.bench = bench;
	}//end setBench

	int getBench() {
		return bench;
	}//end getBench

	void setSquat(int squat) {
		this.squat = squat;
	}//end setSquat

	int getSquat() {
		return squat;
	}//end getSquat

	void setDeadlift(int deadlift) {
		this.deadlift = deadlift;
	}//end setDeadlift

	int getDeadlift() {
		return deadlift;
	}//getDeadlift

	void addMessage(Message message) {
		messageList.add(message);
	}//end addMessage
	
	ArrayList<Workout> getWorkoutList() {
		return workoutList;
	}//end getWorkoutList

	ArrayList<Message> getMessageList() {
		return messageList;
	}//end getMessageList

	String getInfo() {
		return name + " | " + accountStatus + " | " + trainer;
	}//getInfo

	void addWorkout(String creater) {
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);
		System.out.print("Enter name of workout: ");
		String nameOfWorkout = inputString.nextLine();
		System.out.print("Enter Date (MM/DD/YY): ");
		String date = inputString.nextLine();
		System.out.print("Was workout completed (complete or uncomplete)?: ");
		String completion = inputString.nextLine();
		Workout workout = new Workout(creater, nameOfWorkout, date, completion);

		//add exercises
		System.out.print("How many exercises would you like to add?: ");
		boolean validInputE = false;
		int CnumberOfExercises = 0;
		while(validInputE == false) {
			if(input.hasNextInt()) {
				int numberOfExercises = input.nextInt();
				if(numberOfExercises < 1 || numberOfExercises > 20) {
					System.out.print("Invalid input. Please input an integer: ");
				}
				else {
					CnumberOfExercises = numberOfExercises;
					validInputE = true;
				}
			}
		}//end validator

		for(int i = 0; i < CnumberOfExercises; i++) {
			workout.addExercise();
		}

		workoutList.add(workout);
	}//addWorkout

	void listWorkouts() {
		Iterator<Workout> i = workoutList.iterator();
		System.out.println("\nWorkout | Date | Creater | Completion Status");
		while(i.hasNext()) {
			Workout workout = i.next();
			System.out.println(workout.getInfo());
		}
	}//end listWorkouts

	void printExercise() {
		Scanner inputString = new Scanner(System.in);
		System.out.print("Enter name of workout: ");
		String name = inputString.nextLine();
		System.out.print("Enter date: ");
		String date = inputString.nextLine();
		Iterator<Workout> i = workoutList.iterator();

		while(i.hasNext()){
			Workout workout = i.next();
			if (name.equals(workout.getName()) && date.equals(workout.getDate())) {
				workout.listExercises();
			}
		}
	}//end printExercise

	void listMessages() {
		Iterator<Message> i = messageList.iterator();
		System.out.println("\nSender | Status | Date Recieved");
		while(i.hasNext()) {
			Message message = i.next();
			System.out.println(message.getInfo());
		}
	}//end listMessages

	void viewMessage() {
		System.out.print("\nEnter name of sender whose messages you would like to view: ");
		Scanner inputString = new Scanner(System.in);
		String cSelection = "";
		boolean validInput = false;
		while(validInput == false) {
			if(inputString.hasNextLine()) {
				String selection = inputString.nextLine();
				//avoid case sensitive input
				selection = selection.toLowerCase();
				Iterator<Message> i = this.messageList.iterator();
				while(i.hasNext()) {
					Message message = i.next();
					String match = message.getSender();
					match = match.toLowerCase();
					if(selection.equals(match)) {
						validInput = true;
						cSelection = selection;
					}
				}
				if(validInput == false) {
					System.out.print("Match was not found. Try Again: ");
				}
			}
			else {
				inputString.reset();
				System.out.print("Match was not found. Try again: ");
			}

		}//end validator
	

		//print messages
		Iterator<Message> ii = this.messageList.iterator();
		while(ii.hasNext()) {
			Message mmessage = ii.next();
			String mmSender = mmessage.getSender();
			mmSender = mmSender.toLowerCase();
			if(cSelection.equals(mmSender)) {
				System.out.println(mmessage.getMessage());
			}
		}//end print message

	}//end viewMessage

	@Override void menu() {
	
		//loads ArrayList 
		ArrayList<Member> memberList = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream("memberList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			memberList = (ArrayList<Member>)ois.readObject();
			ois.close();
			fis.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}

		System.out.println("\nWelcome " + name + "!");

		boolean active = true;
		while(active == true) {
			System.out.println("------------------------------\n");
			System.out.println("What would you like to do?:");
			System.out.println("1:Display Workouts \n2:View Inbox \n3:Add Workout \n4:Exit");
			System.out.print("Enter Selection: ");
	
			//take in input and validate 
			Scanner input = new Scanner(System.in);
			boolean validInput = false;
			int selection = 0;
			while(validInput == false) {
				if(input.hasNextInt()) {
					int Vselection = input.nextInt();
					if(Vselection < 1 || Vselection > 4) {
						System.out.print("Invalid input. Please select one of the options: ");
					}
					else {
						selection = Vselection;
						validInput = true;
					}
				}
			}//end input validator


			if(selection == 1) {
				if(workoutList.size() < 1) {
					System.out.println("\nYou currently don't have any workouts.\n");
				}
				else {

					this.listWorkouts();
					System.out.println("\nWould you like to view the exercises in a specific workout?");
					System.out.print("[1]Yes   [2]No : ");
					//validate input 
					boolean validInput1 = false;
					int selection1 = 0;
					while(validInput1 == false) {
						if(input.hasNextInt()) {
							int Vselection1 = input.nextInt();
							if(Vselection1 < 1 || Vselection1 > 2) {
								System.out.print("Invalid input. Please select one of the options: ");
							}
							else {
								selection1 = Vselection1;
								validInput1 = true;
							}
						}
						else {
							System.out.print("Invalid input. Please select on of the options: ");
							input.next();
						}
					}//end validator1
	
					if(selection1 == 1) {
						this.printExercise();
					}
					else {
						System.out.println("");
					}
				}
			}//end selection 1

			if(selection == 2) {
				if(messageList.size() < 1) {
					System.out.println("\nYou currently don't have any messages\n");
				}
				else {
					this.listMessages();
					System.out.println("\nWould you like to view messages from someone?");
					System.out.print("[1]Yes  [2]No : ");
					//input validator
					boolean validInput2 = false;
					int selection2 = 0;
					while(validInput2 == false) {
						if(input.hasNextInt()) {
							int Vselection2 = input.nextInt();
							if(Vselection2 < 1 || Vselection2 > 2) {
								System.out.print("Invalid input. Please select one of the options: ");
							}
							else {
								selection2 = Vselection2;
								validInput2 = true;
							}
						}
						else {
							System.out.print("Invalid input. Please select one of the options: ");
						}
					}//end validator2

					if(selection2 == 1) {
						this.viewMessage();
					}
					else {
						System.out.println("");
					}
				}
			}//end selection 2

			if(selection == 3) {
				this.addWorkout(this.name);
			}//end selection 3

			if(selection == 4) {
				//save changes
				boolean saved = false;
				ListIterator<Member> i4 = memberList.listIterator();
				while(i4.hasNext()) {
					Member m4 = i4.next();
					int password4 = m4.getPassword();
					if(password4 == this.getPassword()) {
						i4.set(new Member(this.getName(), this.getPassword(), this.getAccountStatus(), this.getAge(), this.getTrainer(), this.getYearsOfExp(), this.getBench(), this.getSquat(), this.getDeadlift(), this.getWorkoutList(), this.getMessageList()));
						saved = true;
					}
				}

				//save to file
				try {
					FileOutputStream fos = new FileOutputStream("memberList.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(memberList);
					oos.close();
					fos.close();
				}
				catch (IOException e) {
					System.out.println(e);
				}

				System.out.println("Successfully exited. Have a great rest of your day!");
				active = false;
			}//end selection 4
		}//end while active
	}//end menu
}//end Member class
