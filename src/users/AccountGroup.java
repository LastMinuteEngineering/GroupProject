package users;

import java.util.ArrayList;

public class AccountGroup {
	private String name;
	private String id;
	private String description;
	private ArrayList<UserAccount> members;
	
	public AccountGroup(String name, String id, String description) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.members = new ArrayList<>();
	}
	
	public ArrayList<UserAccount> getMembers() {
		return members;
	}
	
	public void addMember(UserAccount user) {
		if(members.contains(user)) {
			System.out.println(user.getName() + " already in " + name);
			return;
		}
		
		members.add(user);
	};
}
