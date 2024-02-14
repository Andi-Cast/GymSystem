//Message.java

import java.io.*;
import java.util.*;

class Message implements Serializable {
	private String sender;
	private String message;
	private String status;
	private String date;

	Message() {
		sender = "sender";
		message = "random message";
		status = "unread";
	}//end Message

	Message(String sender, String date, String message) {
		this.sender = sender;
		this.date = date;
		this.message = message;
		status = "unread";
	}//end Message()

	String getSender() {
		return sender;
	}//end getSender

	String getInfo() {
		return sender + " | " + status + " | " + date;
	}//end getInfo

	String getMessage() {
		status = "read";
		return "\n" + message + "        " + date + "\n";
	}//end getMessage
}//end Message class
