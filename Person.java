//Person.java

import java.io.*;
import java.util.*;

abstract class Person implements Serializable {
	protected String name;
	protected int age;
	protected int password;
	protected String accountStatus;

	void setName(String name){
		this.name = name;
	}//end setName

	String getName() {
		return name;
	}//end getName

	void setPassword(int password) {
		this.password = password;
	}//end setPassword

	int getPassword() {
		return password;
	}//end getPassword

	void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}//end setAccountStatus

	String getAccountStatus() {
		return accountStatus;
	}//end getAccountStatus

	void setAge(int age) {
		this.age = age;
	}//end setAge

	int getAge() {
		return age;
	}//end getAge

	abstract void menu();

}//end Member class
