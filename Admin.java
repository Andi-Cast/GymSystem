//Admin.java

import java.io.*;
import java.util.*;

class Admin extends Person implements Serializable {
	Admin() {
		name = "Unknown";
		password = 00000;
		accountStatus = "Active";
		age = 0;
	}//end Admin

	Admin(String name, int password, int age) {
		this.name = name;
		this.password = password;
		this.accountStatus = "Active";
		this.age = age;
	}//end Admin()
	
	void listMembers(ArrayList<Member> memberList) {
		Iterator<Member> i = memberList.iterator();
		System.out.println("Name | Account Status | Trainer");
		while(i.hasNext()) {
			Member member = i.next();
			System.out.println(member.getInfo());
		}
	}//end listMembers

	void listTrainers(ArrayList<Trainer> trainerList) {
		Iterator<Trainer> i = trainerList.iterator();
                System.out.println("Name | Account Status ");
                while(i.hasNext()) {
                        Trainer trainer = i.next();
                        System.out.println(trainer.getInfo());
                }
	}//end listTrainer

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

		//load trainers
		ArrayList<Trainer> trainerList = new ArrayList<>();
                try {
                        FileInputStream fis = new FileInputStream("trainerList.ser");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        trainerList = (ArrayList<Trainer>)ois.readObject();
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
			System.out.println("What would you like to do?:");
			System.out.println("[1]Display Members\n[2]Display Trainers\n[3]Ban Person\n[4]Unban Person\n[5]Exit");
			System.out.print("Enter Selection: ");

			//validate input
			boolean validInput = false;
			int selection = 0;
			while(validInput == false) {
				if(input.hasNextInt()) {
					int cSelection = input.nextInt();
					if(cSelection < 1 || cSelection > 5) {
						System.out.print("Invalid input. Please select one of the options: ");
					}
					else {
						selection = cSelection;
						validInput = true;
					}
				}
			}//end validInput

			if(selection == 1) {
				listMembers(memberList);
			}//end selection 1
			
			if(selection == 2) {
				listTrainers(trainerList);
			}//end selection 2

			if(selection == 3) {
				System.out.print("Would you like to ban a [1]Member or [2]Trainer: ");
				boolean validInput3 = false;
				int selection3 = 0;
				while(validInput3 == false) {
					if(input.hasNextInt()) {
						int cSelection3 = input.nextInt();
						if(cSelection3 < 1 || cSelection3 > 2) {
							System.out.print("Invalid input. Please select one of the options: ");
						}
						else {
							selection3 = cSelection3;
							validInput3 = true;
						}
					}
				}//end validator3

				if(selection3 == 1) {
					System.out.print("Enter name of member you wish to ban: ");
					boolean banMemberMatch = false;
					String banMemberName = "";
					while(banMemberMatch == false) {
						if(inputString.hasNextLine()) {
							String cBanMemberName = inputString.nextLine();
							Iterator<Member> i31 = memberList.iterator();
							while(i31.hasNext()) {
								Member memberBan = i31.next();
								if(cBanMemberName.equals(memberBan.getName())) {
									banMemberMatch = true;
									banMemberName = cBanMemberName;
								}
							}
							if (banMemberMatch == false) {
								System.out.print("Match was not found. Try again: ");
							}
						}
						else {
							inputString.reset();
							System.out.print("Match was not found. Try again: ");
						}
					}

					Iterator<Member> ii31 = memberList.iterator();
					int counterBanMember = 0;
					while(ii31.hasNext()) {
						Member Bmember = ii31.next();
						if(banMemberName.equals(Bmember.getName())) {
							memberList.get(counterBanMember).setAccountStatus("Banned");
						}
						counterBanMember = counterBanMember + 1;
					}
				}//end selection 1 (3)

				if(selection3 == 2) {
					System.out.print("Enter name of trainer you wish to ban: ");
                                        boolean banTrainerMatch = false;
                                        String banTrainerName = "";
                                        while(banTrainerMatch == false) {
                                                if(inputString.hasNextLine()) {
                                                        String cBanTrainerName = inputString.nextLine();
                                                        Iterator<Trainer> i32 = trainerList.iterator();
                                                        while(i32.hasNext()) {
                                                                Trainer trainerBan = i32.next();
                                                                if(cBanTrainerName.equals(trainerBan.getName())) {
                                                                        banTrainerMatch = true;
                                                                        banTrainerName = cBanTrainerName;
                                                                }
                                                        }
                                                        if (banTrainerMatch == false) {
                                                                System.out.print("Match was not found. Try again: ");
                                                        }
                                                }
                                                else {
                                                        inputString.reset();
                                                        System.out.print("Match was not found. Try again: ");
                                                }
                                        }

                                        Iterator<Trainer> ii32 = trainerList.iterator();
                                        int counterBanTrainer = 0;
                                        while(ii32.hasNext()) {
                                                Trainer Btrainer = ii32.next();
                                                if(banTrainerName.equals(Btrainer.getName())) {
                                                        trainerList.get(counterBanTrainer).setAccountStatus("Banned");
                                                }
                                                counterBanTrainer = counterBanTrainer + 1;
                                        }
				}//end selection 2 (3)
			}//end selection 3

			if(selection == 4) {
				System.out.print("Would you like to unban a [1]Member or [2]Trainer: ");
                                boolean validInput4 = false;
                                int selection4 = 0;
                                while(validInput4 == false) {
                                        if(input.hasNextInt()) {
                                                int cSelection4 = input.nextInt();
                                                if(cSelection4 < 1 || cSelection4 > 2) {
                                                        System.out.print("Invalid input. Please select one of the options: ");
                                                }
                                                else {
                                                        selection4 = cSelection4;
                                                        validInput4 = true;
                                                }
                                        }
                                }//end validator4

                                if(selection4 == 1) {
                                        System.out.print("Enter name of member you wish to unban: ");
                                        boolean unbanMemberMatch = false;
                                        String unbanMemberName = "";
                                        while(unbanMemberMatch == false) {
                                                if(inputString.hasNextLine()) {
                                                        String cUnbanMemberName = inputString.nextLine();
                                                        Iterator<Member> i41 = memberList.iterator();
                                                        while(i41.hasNext()) {
                                                                Member memberUnban = i41.next();
                                                                if(cUnbanMemberName.equals(memberUnban.getName())) {
                                                                        unbanMemberMatch = true;
                                                                        unbanMemberName = cUnbanMemberName;
                                                                }
                                                        }
                                                        if (unbanMemberMatch == false) {
                                                                System.out.print("Match was not found. Try again: ");
                                                        }
                                                }
                                                else {
                                                        inputString.reset();
                                                        System.out.print("Match was not found. Try again: ");
                                                }
                                        }

                                        Iterator<Member> ii41 = memberList.iterator();
                                        int counterUnbanMember = 0;
                                        while(ii41.hasNext()) {
                                                Member Umember = ii41.next();
                                                if(unbanMemberName.equals(Umember.getName())) {
                                                        memberList.get(counterUnbanMember).setAccountStatus("Active");
                                                }
                                                counterUnbanMember = counterUnbanMember + 1;
                                        }
                                }//end selection 2 (4)
				
				if(selection4 == 2) {
                                        System.out.print("Enter name of trainer you wish to unban: ");
                                        boolean unbanTrainerMatch = false;
                                        String unbanTrainerName = "";
                                        while(unbanTrainerMatch == false) {
                                                if(inputString.hasNextLine()) {
                                                        String cUnbanTrainerName = inputString.nextLine();
                                                        Iterator<Trainer> i42 = trainerList.iterator();
                                                        while(i42.hasNext()) {
                                                                Trainer trainerUnban = i42.next();
                                                                if(cUnbanTrainerName.equals(trainerUnban.getName())) {
                                                                        unbanTrainerMatch = true;
                                                                        unbanTrainerName = cUnbanTrainerName;
                                                                }
                                                        }
                                                        if (unbanTrainerMatch == false) {
                                                                System.out.print("Match was not found. Try again: ");
                                                        }
                                                }
                                                else {
                                                        inputString.reset();
                                                        System.out.print("Match was not found. Try again: ");
                                                }
                                        }

                                        Iterator<Trainer> ii42 = trainerList.iterator();
                                        int counterUnbanTrainer = 0;
                                        while(ii42.hasNext()) {
                                                Trainer Utrainer = ii42.next();
                                                if(unbanTrainerName.equals(Utrainer.getName())) {
                                                        trainerList.get(counterUnbanTrainer).setAccountStatus("Active");
                                                }
                                                counterUnbanTrainer = counterUnbanTrainer + 1;
                                        }
                                }//end selection 2 (4)
			}//end selection 4

			if(selection == 5) {
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

				//save trainerList
				try {
                                        FileOutputStream fos = new FileOutputStream("trainerList.ser");
                                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                                        oos.writeObject(trainerList);
                                        oos.close();
                                        fos.close();
                                }
                                catch (IOException e) {
                                        System.out.print(e);
                                }

				active = false;
				System.out.println("Successfully exited. Have a great rest of your day!");
			}//end selection 5
		}

	}//end menu()

}//end of Person
