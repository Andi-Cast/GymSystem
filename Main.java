//Main.java

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		//Store member and trainers
		ArrayList<Member> memberList = new ArrayList<>();
		ArrayList<Trainer> trainerList = new ArrayList<>();
		
		
		//test login
		//Member and Trainers
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

		try {
                        FileInputStream fis = new FileInputStream("trainerList.ser");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        trainerList = (ArrayList<Trainer>)ois.readObject();
                        ois.close();
                        fis.close();
                }
                catch (IOException e) {
                        System.out.println(e);
                }
                catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                }

		//login
		boolean login = false;
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);
		System.out.println("Login Screan");

		while(login == false) {
			System.out.println("------------------------------");
			System.out.print("Are you [1]Member [2]Trainer [3]Admin: ");

			boolean validInput = false;
			int selection = 0;
			while(validInput == false) {
				if(input.hasNextInt()) {
					int cSelection = input.nextInt();
					if(cSelection < 1 || cSelection > 3) {
						System.out.print("Invalid input. Please select one of the options: ");
					}
					else {
						selection = cSelection;
						validInput = true;
					}
				}
			}

			
			if(selection == 1) {
				boolean matchM = false;
				System.out.println("------------------------------");
				System.out.print("Enter Name: ");
				String MLoginName = inputString.nextLine();
				System.out.print("Enter Password: ");
				int MLoginPassword = input.nextInt();
				
				ListIterator<Member> m = memberList.listIterator();
				while(m.hasNext()) {
					Member Lmember = m.next();
					if(Lmember.getName().equals(MLoginName) && Lmember.getPassword() == MLoginPassword && Lmember.getAccountStatus().equals("Active")) {
						System.out.println("Login was successful");
						login = true;
						matchM = true;
						Lmember.menu();
					}
				}

				if (!matchM) {
					System.out.println("No match was found in system or you have been banned. Speak to an admin to find out.");
				}

			}//end selection 1

			if(selection == 2) {
				boolean matchT = false;
                                System.out.println("------------------------------");
                                System.out.print("Enter Name: ");
                                String TLoginName = inputString.nextLine();
                                System.out.print("Enter Password: ");
                                int TLoginPassword = input.nextInt();

                                ListIterator<Trainer> t = trainerList.listIterator();
                                while(t.hasNext()) {
                                        Trainer Ltrainer = t.next();
                                        if(Ltrainer.getName().equals(TLoginName) && Ltrainer.getPassword() == TLoginPassword && Ltrainer.getAccountStatus().equals("Active")) {
                                                System.out.println("Login was successful");
                                                login = true;
                                                matchT = true;
                                                Ltrainer.menu();
                                        }
                                }

                                if (!matchT) {
                                        System.out.println("No match was found in system or you have been banned. Speak to an admin to find out.");
                                }

			}//end selection 2

			if(selection == 3) {
				System.out.println("------------------------------");
                                System.out.print("Enter Name: ");
                                String ALoginName = inputString.nextLine();
                                System.out.print("Enter Password: ");
                                int ALoginPassword = input.nextInt();

				if(ALoginName.equals("Admin") && ALoginPassword == 00000) {
					Admin admin = new Admin();
					admin.menu();
					login = true;
				}
			}//end selection 3
		}//end login
		


		
		/*
		//Populate files
		Member member = new Member("Andi", 11111, 22, "Omar", 4, 345, 495, 405);
		member.menu();
		memberList.add(member);

		Member member2 = new Member("Alma", 22222, 20, "Andy", 2, 0, 0, 0);
		member2.menu();
		memberList.add(member2);

		Member member3 = new Member("Stephanie" , 33333, 18, "Andy", 1, 500, 650, 780);
		member3.menu();
		memberList.add(member3);

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

		Trainer trainer = new Trainer("Omar", 23, 44444, "Active");
		trainer.menu();
		trainerList.add(trainer);

		Trainer trainer2 = new Trainer("Andy", 30, 55555, "Active");
		trainer2.menu();
		trainerList.add(trainer2);
		
		Trainer trainer3 = new Trainer("Hulk", 500, 88888, "Active");
		trainer3.menu();
		trainerList.add(trainer3);

		try {
                        FileOutputStream fos = new FileOutputStream("trainerList.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(trainerList);
                        oos.close();
                        fos.close();
                }
                catch (IOException e) {
                        System.out.println(e);
                }
		
		*/	
		

		//could be used for demo or test the connection between all classes
		/*		
		//test making a member
		Member member = new Member("Andi", 11111, 22, "Trainer", 4, 345, 495, 405);
		member.menu();
		memberList.add(member);
		
		//save members
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

		Trainer trainer = new Trainer();
		trainer.menu();
		trainerList.add(trainer);
		
		//save trainers
		try {
                        FileOutputStream fos = new FileOutputStream("trainerList.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(trainerList);
                        oos.close();
                        fos.close();
                }
                catch (IOException e) {
                        System.out.println(e);
                }

		Admin admin = new Admin();
		admin.menu();
	
		
		
		
		//load member
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

	
		
		//print members
		Iterator<Member> i = memberList.iterator();
		while(i.hasNext()) {
			Member membertt = i.next();
			if(membertt.getName().equals("Andi")) {
				membertt.menu();
			}
		}

		*/

	}//end main
}//end Main class
