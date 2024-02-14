//Trainer.java

import java.io.*;
import java.util.*;

class Trainer extends Person implements Serializable {
	Trainer() {
		name = "Trainer";
		age = 0;
		password = 22222;
		accountStatus = "Active";
	}//end Trainer

	Trainer(String name, int age, int password, String accountStatus) {
		this.name = name;
		this.age = age;
		this.password = password;
		this.accountStatus = accountStatus;
	}//Trainer()
	
	String getInfo() {
		return name + " | " + accountStatus;
	}//end getInfo()

	@Override void menu() {
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);

		//loads members
		ArrayList<Member> memberList = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream("memberList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			memberList = (ArrayList<Member>)ois.readObject();
			ois.close();
			fis.close();
		}		
		catch (IOException e) {
			System.out.print(e);
		}
		catch (ClassNotFoundException ex) {
			System.out.print(ex);
		}


		System.out.println("Welcome " + name + "\n");
		boolean active = true;
		while(active == true) {
			System.out.println("\n------------------------------\n");
			System.out.println("What would you like to do?: ");
			System.out.println("[1]Display Clients\n[2]Send Workout to Client\n[3]Send Message to Client\n[4]Exit");
			System.out.print("Enter Selection: ");

			//validate selection
			boolean validInput = false;
			int selection = 0;
			while(validInput == false) {
				if(input.hasNextInt()) {
					int cSelection = input.nextInt();
					if(cSelection < 1 || cSelection > 5) {
						System.out.print("Invalid input. Please select one of the options");
					}
					else {
						selection = cSelection;
						validInput = true;
					}
				}
			}//end validator

			if(selection == 1) {
				//display clients
				Iterator<Member> i1 = memberList.iterator();
				int numberOfClients = 0;
				while(i1.hasNext()) {
					Member member1 = i1.next();
					if(this.name.equals(member1.getTrainer())) {
						System.out.println(member1.getInfo());
						numberOfClients = numberOfClients + 1;
					}
				}
				if(numberOfClients == 0) {
					System.out.println("\niYou currently don't have any clients\n");
				}
			}//end selection 1

			if(selection == 2) {
				//check if there is clients
				Iterator<Member> i2 = memberList.iterator();
                                int numberOfClients2 = 0;
                                while(i2.hasNext()) {
                                        Member member2 = i2.next();
                                        if(this.name.equals(member2.getTrainer())) {
                                                numberOfClients2 = numberOfClients2 + 1;
                                        }
                                }

                                if(numberOfClients2 == 0) {
                                        System.out.println("\nYou currently don't have any clients\n");
                                }
				else {
					//send workout
					System.out.print("\nEnter name of client you wish to send a workout to: ");
				        boolean sendWorkoutFound = false;
					String sendWorkoutClient = "";
			       		while(sendWorkoutFound == false) {
						if(inputString.hasNextLine()) {
							String cSendWorkoutClient = inputString.nextLine();
							Iterator<Member> i22 = memberList.iterator();
							while(i22.hasNext()) {
								Member memberSendWorkout = i22.next();
								String testMemberName = memberSendWorkout.getName();
								if(cSendWorkoutClient.equals(testMemberName)) {
									sendWorkoutFound = true;
									sendWorkoutClient = cSendWorkoutClient;
								}
							}
							if(sendWorkoutFound == false) {
								System.out.print("Match was not found. Try again: ");
							}
						}
						else {
							inputString.reset();
							System.out.print("Match was not found. Try again: ");
						}
					}		
					
					Iterator<Member> i222 = memberList.iterator();
					int counterWorkoutMember = 0;
					while(i222.hasNext()) {
						Member Wmember = i222.next();
						if(sendWorkoutClient.equals(Wmember.getName())) {
							memberList.get(counterWorkoutMember).addWorkout(this.name);
						}
					}
				}
                        }//end selection 2

			if(selection == 3) {
				Iterator<Member> i3 = memberList.iterator();
                                int numberOfClients3 = 0;
                                String sendMessageClient = "";
                                while(i3.hasNext()) {
                                        Member member3 = i3.next();
                                        if(this.name.equals(member3.getTrainer())) {
                                                numberOfClients3 = numberOfClients3 + 1;
                                        }
                                }

                                if(numberOfClients3 == 0) {
                                        System.out.println("You currently don't have any clients\n");
                                }
                                else {
                                        //send message
                                        System.out.print("\nEnter name of client you wish to send a message to: ");
                                        boolean sendMessageFound = false;
                                        while(sendMessageFound == false) {
                                                if(inputString.hasNextLine()) {
                                                        String cSendMessageClient = inputString.nextLine();
                                                        Iterator<Member> i33 = memberList.iterator();
                                                        while(i33.hasNext()) {
                                                                Member memberSendMessage = i33.next();
                                                                if(cSendMessageClient.equals(memberSendMessage.getName())) {
                                                                        sendMessageFound = true;
                                                                        sendMessageClient = cSendMessageClient;
                                                                }
                                                        }
                                                        if(sendMessageFound == false) {
                                                                System.out.print("Match was not found. Try again: ");
                                                        }
                                                }
                                                else {
                                                        inputString.reset();
                                                        System.out.print("Match was not found. Try again: ");
                                                }
                                        }

                                        Iterator<Member> i333 = memberList.iterator();
                                        int counterMessageMember = 0;
                                        while(i333.hasNext()) {
                                                Member Mmember = i333.next();
                                                if(sendMessageClient.equals(Mmember.getName())) {
							System.out.print("Type message: ");
							String messageFromTrainer = inputString.nextLine();
							System.out.print("Date (MM/DD/YY): ");
							String messageDate = inputString.nextLine();
							Message messageSent = new Message(this.name, messageDate, messageFromTrainer);
                                                        memberList.get(counterMessageMember).addMessage(messageSent);
                                                }
                                        }
                                }
                        }//end selection 3

			if(selection == 4) {
				//save memberList
				try {
					FileOutputStream fos = new FileOutputStream("memberList.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(memberList);
					oos.close();
					fos.close();
				}
				catch (IOException e) {
					System.out.print(e);
				}

				active = false;
				System.out.println("Successfully exited. Have a great rest of your day!");
                        }//end selection 4
		}//end while active
	}//end menu


}//class Trainer
