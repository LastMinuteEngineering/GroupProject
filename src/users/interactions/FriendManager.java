package users.interactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Iterator;

import users.UserAccount;
import external.Registrar;
import common.Utils;
import common.types.CustomEnums.*;

public class FriendManager {
	private ArrayList<UserAccount> friends;
	private HashMap<UserAccount, String> sentRequests;
	private HashMap<UserAccount, String> receivedRequests;
	private Registrar registrar;
	private Scanner in;
	
 	public FriendManager() {
		this.friends = new ArrayList<>();
		this.sentRequests = new HashMap<>();
		this.receivedRequests = new HashMap<>();
		this.registrar = Registrar.getInstance();
		this.in = new Scanner(System.in);
	}
 	
 	public void sendRequest(String toAdd, String msg, UserAccount sender) {
 		UserAccount user = registrar.findUser(toAdd);
 		
 		// confirm user exists and there is not already a pending friend request.
 		if (user == null) {
 			System.out.println("Unable to find user: " + toAdd + "\n");
 			return;
 		}
 		else if( sentRequests.getOrDefault(user, null) != null) {
 			System.out.println("Already sent friend request to user: " + toAdd + "\n");
 			return;
 		}
 		else if (receivedRequests.getOrDefault(user, null) != null) {
 			System.out.println("User has a pending friend request. Please accept or reject their request.\n");
 			return;
 		}
 		
 		sentRequests.put(user, msg);
 		user.receiveFriendRequest(sender, msg);
 		
 		System.out.println("Sent request to user.\n");

 		
 	}
 	
 	
 	public void addRequest(UserAccount sender, String msg) {
 		receivedRequests.put(sender, msg);
 	}
 	
 	public void handleFriendRequests(UserAccount parentAccount) {
 		
		Iterator<Entry<UserAccount, String>> requestIterator = receivedRequests.entrySet().iterator(); 
		
		// Handle case: no requests in list.
		if (!requestIterator.hasNext()) {
			System.out.println("No active friend requests. \n");
			return;
		}
		
		while(requestIterator.hasNext()) {
			Entry<UserAccount, String> request = requestIterator.next();
			UserAccount user = request.getKey();
			String requestMsg = request.getValue();
			
			getRequestResponse(user, requestMsg, parentAccount);
		}
 		
 	}
 	
 	public Iterator<UserAccount> getFriends(){
 		return friends.iterator();
 		
 	}
 	
 	public void addFriend(UserAccount friend) {
 		friends.add(friend);
 	}
 	
 	public void removePendingRequest(UserAccount user) {
 		sentRequests.remove(user);
 	}
 	
 	private void getRequestResponse(UserAccount sender, String requestMsg, UserAccount parentAccount) {
 		String [] allowableValues = {RequestResponse.Accept.description, RequestResponse.Reject.description} ;
 		// prompt user based on data; if toModify has data, display it and prompt user.
		System.out.println(
				sender.getName() + " would like to add you as a friend."
				+ "\n" + "\"" + requestMsg + "\""
				+ "\nAccept or Reject (allowable values: " 
				+ Utils.listToString(allowableValues) + "):");
	
		String response = in.nextLine();
		
		// Loop while user input does not match one of the expected vals.
		while(!Utils.stringContainsItemFromList(response, allowableValues)) {
			System.out.println( response + " is not a valid choice. Please try again (allowable values: " + Utils.listToString(allowableValues) + ") :");
			response = in.nextLine();
		}
		
		switch(RequestResponse.toEnum(response)) {
		case Accept : 
			addFriend(sender);
			sender.addFriend(parentAccount);
			break;
		case Reject :
			receivedRequests.remove(sender);
			sender.removeFriendRequest(parentAccount);
			break;
		default:
			break;
		}
 	}
 	
 	
 	
}
